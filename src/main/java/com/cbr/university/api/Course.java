package com.cbr.university.api;

import com.cbr.university.service.BaseService;
import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.Update;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/courses")
@Validated
public class Course {
    private final BaseService<com.cbr.university.model.Course> courseService;

    @GetMapping
    public List<com.cbr.university.model.Course> getAll() {
        return courseService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public com.cbr.university.model.Course add(@Validated(Create.class) @RequestBody com.cbr.university.model.Course course) {
        return courseService.create(course);
    }

    @PutMapping
    public com.cbr.university.model.Course update(@Validated(Update.class) @RequestBody com.cbr.university.model.Course course) {
        return courseService.update(course);
    }

    @DeleteMapping("{id}")
    public void delete(
            @NotNull(message = "Request must include a Course id")
            @IdExistsInDb(typeObject = "Course", message = "This Course id is not in the database")
            @PathVariable Integer id) {
        courseService.deleteById(id);
    }
}
