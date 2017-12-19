package chaos.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
class BaseCoreController {
    /**
     * The Request.
     *
     * @see BaseCoreController#request BaseController#request request
     */
    @Autowired
    protected HttpServletRequest request;
    /**
     * response
     */
    @Autowired
    protected HttpServletResponse response;



}
