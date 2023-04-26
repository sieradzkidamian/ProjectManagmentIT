package com.fit_web_app.services;

import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.entity.UsersEntity;
import com.fit_web_app.services.bl.*;
import net.minidev.json.JSONObject;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;

@EnableWebMvc
@RestController
public class Endpoints implements ErrorController {
    private final CoursesBl coursesBl;

    private final StepsBl stepsBl;

    private final UserBl userBl;

    private final UserDataBl userDataBl;

    private final ResourceBundle messages = ResourceBundle.getBundle("messages");

    public Endpoints() {
        coursesBl = new CoursesBlImpl();
        stepsBl = new StepsBlImpl();
        userBl = new UserBlImpl();
        userDataBl = new UserDataBlImpl();
    }

@PostMapping(value = "/getUserByLogin")
public ResponseEntity<?> getUserByLogin(@RequestBody HashMap<String, String> parameters, @RequestHeader HashMap<String, String> headers) {
        try {
        UsersEntity user = userBl.getUserByLogin(parameters.get("login"), headers.get("authorization"));
        user.setPassword("");

        return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception exception) {
        return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

@PostMapping(value = "/registerUser")
public ResponseEntity<?> registerUser(@RequestBody HashMap<String, String> parameters) {
        try {
        userBl.registerUser(parameters.get("login"), parameters.get("password"), parameters.get("email"), Integer.parseInt(parameters.get("roleId")));
        return new ResponseEntity<>(Collections.singletonMap("message", messages.getString("messageUserHasBeenAdded")), HttpStatus.CREATED);
        } catch (DevException exception) {
        return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.OK);
        } catch (Exception exception) {
        return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

    @PostMapping(value = "/getAllCourses")
    public ResponseEntity<?> getAllCourses() {
        try {
            return new ResponseEntity<>(coursesBl.getAllCourses(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/getCourses")
    public ResponseEntity<?> getCourses(@RequestBody HashMap<String, Integer> parameters) {
        try {
            return new ResponseEntity<>(coursesBl.getCourse(parameters.get("courseId")), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addCourse")
    public ResponseEntity<?> addCourse(@RequestBody HashMap<String, String> parameters) {
        try {
            coursesBl.addCourse(parameters.get("name"), parameters.get("description"), parameters.get("image"));
            return new ResponseEntity<>(Collections.singletonMap("message", messages.getString("messageCourseHasBeenAdded")), HttpStatus.OK);
        } catch (DevException exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/deleteCourse")
    public ResponseEntity<?> deleteCourse(@RequestBody HashMap<String, Integer> parameters) {
        try {
            coursesBl.deleteCourse(parameters.get("courseId"));
            return new ResponseEntity<>(Collections.singletonMap("message", messages.getString("messageCourseHasBeenDeleted")), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/editCourse")
    public ResponseEntity<?> editCourse(@RequestBody HashMap<String, String> parameters) {
        try {
            coursesBl.editCourse(Integer.valueOf(parameters.get("id")), parameters.get("name"), parameters.get("description"), parameters.get("image"));
            return new ResponseEntity<>(Collections.singletonMap("message", messages.getString("messageCourseHasBeenChanged")), HttpStatus.OK);
        } catch (DevException exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }