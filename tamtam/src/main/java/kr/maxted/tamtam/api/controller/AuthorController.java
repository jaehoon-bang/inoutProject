package kr.maxted.tamtam.api.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author devkimsj
 *
 */
@SuppressWarnings("Duplicates")
@RestController
@RequestMapping(value = "/authors", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthorController {

}