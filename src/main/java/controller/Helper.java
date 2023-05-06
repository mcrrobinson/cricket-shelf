/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;

/**
 *
 * @author Squash
 */
public class Helper {
    static HashMap<Integer, String> statuses  = new HashMap<Integer, String>() {{
        put(1, "Ordered");
        put(2, "Out For Delivery");
        put(3, "Delivered");
        put(4, "Cancelled");
    }};
}
