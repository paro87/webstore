package com.paro.controller;


import com.paro.domain.Product;
import com.paro.exception.NoProductsFoundUnderCategoryException;
import com.paro.exception.ProductNotFoundException;
import com.paro.service.ProductService;
import com.paro.validator.ProductImageValidator;
import com.paro.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("market")
public class ProductController {



    @Autowired
    private ProductService productService;

    @Autowired
    private ProductValidator productValidator;

    @Autowired
    private ProductImageValidator productImageValidator;

    @RequestMapping("/products")
    public String list(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @RequestMapping("/update/stock")
    public String updateStock(Model model) {
        productService.updateAllStock();
        return "redirect:market/products";
    }

    /*
    In order to do the binding, Spring MVC internally uses a special binding
object called WebDataBinder (org.springframework.web.bind.WebDataBinder).
WebDataBinder extracts the data out of the HttpServletRequest object and converts it to
a proper data format, loads it into a form backing bean, and validates it. To customize the
behavior of data binding, we can initialize and configure the WebDataBinder object in our
Controller. The @InitBinder(org.springframework.web.bind.annotation.InitBinder) annotation helps us to do
that. The @InitBinder annotation designates a method to initialize WebDataBinder.The @InitBinder annotation designates a Controller method as a hook method to do some
custom configuration regarding data binding on WebDataBinder. And WebDataBinder is
the thing that is doing the data binding at runtime, so we need to specify which fields are
allowed to bind to WebDataBinder.
     */
    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {
        binder.setValidator(productValidator);
        binder.setValidator(productImageValidator);
        binder.setAllowedFields("productId",
                "name",
                "unitPrice",
                "description",
                "manufacturer",
                "category",
                "unitsInStock",
                "condition",
                "productImage",
                "language");

    }

    /*
    If the @PathVariable annotation is specified without any value
attribute, it will try to retrieve a path variable with the name of the
variable it has been annotated with.
For example, if you specify simply @PathVariable String productId,
then Spring will assume that it should look for a URI template variable
{productId} in the URL. A request mapping method can have any
number of @PathVariable annotations.
     */
    //http://localhost:8080/webstore/market/products/Tablet
    @RequestMapping("/products/{category}")
    public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
        List<Product> products = productService.getProductsByCategory(productCategory);
        if (products == null || products.isEmpty()) {
            throw new NoProductsFoundUnderCategoryException();
        }
        model.addAttribute("products", products);
        return "products";
    }

    /*
    1-A URL can have multiple matrix variables, and each matrix variable must
    be separated with a “;” (semicolon).
    http://localhost:8080/webstore/market/products/filter/
    params;brands=Google;brands=Dell;categories=Tablet;categories=Laptop

    2-http://localhost:8080/webstore/market/products/filter/params;brands=Google,Dell;
    categories=Tablet,Laptop/specification;dimention=10,20,15;color=red,green,blue
    @RequestMapping("/products/filter/{params}/{specification}")
    public String filter(@MatrixVariable(pathVar="params") Map<String,List<String>> criteriaFilter,
    @MatrixVariable(pathVar="specification") Map<String,List<String>> specFilter, Model model)
     */
    //http://localhost:8080/webstore/market/products/filter/params;brands=Google,Dell;categories=Tablet,Laptop
    @RequestMapping("/products/filter/{params}")
    public String
    getProductsByFilter(@MatrixVariable(pathVar="params") Map<String, List<String>> filterParams, Model model) {
        model.addAttribute("products", productService.getProductsByFilter(filterParams));
        return "products";
    }

    /*
    1-since we annotated the parameter productId with @RequestParam("id") annotation
    (org.springframework.web.bind.annotation.RequestParam), Spring MVC will try
    to read a GET request parameter with the name id from the URL and will assign that to the
    getProductById method's parameter productId
    2-if the name of the GET request parameter and the name of the variable
    it is annotating are the same, then there is no need to specify the value attribute in the
    @RequestParam annotation
    3-passing more than one GET request parameter in the URL
    http://localhost:8080/webstore/product?category=laptop&price=700
    public String getProducts(@RequestParam String category, @RequestParam String price) {
     */
    //http://localhost:8080/webstore/market/product?id=P1234
    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "product";
    }

    //http://localhost:8080/webstore/market/products/Tablet/price;low=200;high=400?brand="Google"
    //http://localhost:8080/webstore/market/productsB?brand=Google
    @RequestMapping("/products/{category}/{params}")
    //@RequestMapping("/productsB")
    public String filterProducts(Model model, @PathVariable("category") String productCategory, @MatrixVariable(pathVar="params") Map<String, List<String>> filterParams, @RequestParam("brand") String manufacturer) {
    //public String filterProducts(@RequestParam("brand") String manufacturer, Model model) {
        model.addAttribute("products", productService.getProductsByCategory(productCategory));
        model.addAttribute("products", productService.getProductsByPriceFilter(filterParams));
        model.addAttribute("product", productService.getProductByManufacturer(manufacturer));

        return "product";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.GET)
    public String getAddNewProductForm(Model model) {
        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);
        return "addProduct";
    }

    /*Spring tag library tags help us to bind the HTML tag element's values to a form backing bean in the Model. Later, the Controller can retrieve the form
    backing bean from the Model using the @ModelAttribute (org.springframework.web.bind.annotation.ModelAttribute) annotation
    The form backing bean (sometimes called the form bean) is used to store
    form data. We can even use our domain objects as form beans; this works
    well when there's a close match between the fields in the form and the
    properties in our domain object. Another approach is creating separate
    classes for form beans, which is sometimes called Data Transfer Objects
    (DTO).
    */
    /*
    This method will be invoked once we press the submit
    button on our form. Yes, since every form submission is considered a POST request, this
    time the browser will send a POST request to the same URL
    http://localhost:8080/webstore/products/add
     */
    /*
    !!! @ModelAttribute("newProduct") Product newProduct -- "new product" must be the same with the "new product" in <form:form method="POST" modelAttribute="newProduct" class="form-horizontal">
     */
    /*
     instead of returning a View name, we are simply instructing Spring to issue a redirect request to the
request path /market/products, which is the request path for the list method of our
ProductController. This pattern is called redirect-after-post; it is a commonly used pattern with
web-based forms. We are using this pattern to avoid double submission of the same form.
     */
    /*
    We changed processAddNewProductForm by adding one extra parameter called result,
which is of the type BindingResult. Spring MVC will fill this object with the result of the
binding. If any attempt is made to bind any fields other than the allowed fields, the
BindingResult object will have a getSuppressedFields count greater than zero. That's
why we were checking the suppressed field count and throwing a RuntimeException
exception:
     */
    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct, BindingResult result, HttpServletRequest request) {
        if(result.hasErrors()) {
            return "addProduct";
        }
        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: " +
            StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        MultipartFile productImage = newProduct.getProductImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        /*
        D:\Programming\JAVA\JavaEEWorkspace\webstore\out\artifacts\webstore_war_exploded\
        We wanted to save the image file in the server under the resources/images directory, as
this directory structure will be available directly under the root directory of our web
application at runtime. So, in order to get the root directory of our web application, we need
HttpServletRequest. See the following code snippet:
String rootDirectory = request.getSession().getServletContext().getRealPath("/");
That's the reason we added an extra method parameter called request of the type
HttpServletRequest to our processAddNewProductForm method in step 10.
Remember, Spring will fill this request parameter with the actual HTTP request.
         */

        System.out.println(rootDirectory);
        if (productImage!=null && !productImage.isEmpty()) {
            System.out.println(productImage.getName());
            System.out.println(rootDirectory+"resources\\images"+ newProduct.getProductId() + ".png");

            try {

                productImage.transferTo(new File(rootDirectory+"resources\\images\\"+ newProduct.getProductId() + ".png"));
            } catch (Exception e) {
                throw new RuntimeException("Product Image saving failed", e);
            }
        }
        productService.addProduct(newProduct);

        return "redirect:/market/products";
    }

    //http://localhost:8080/webstore/customers/add

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handleError(HttpServletRequest req, ProductNotFoundException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("invalidProductId", exception.getProductId());
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL()+"?"+req.getQueryString());
        mav.setViewName("productNotFound");
        return mav;
    }

    @RequestMapping("/products/invalidPromoCode")
    public String invalidPromoCode() {
        return "invalidPromoCode";
    }





}