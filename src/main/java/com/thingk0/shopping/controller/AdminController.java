package com.thingk0.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    /**
     * 상품 등록
     */
    @GetMapping(value = "/item/new")
    public String itemFrom() {
        return "item/itemForm";
    }
}
