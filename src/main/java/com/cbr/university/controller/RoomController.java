package com.cbr.university.controller;

import com.cbr.university.model.Room;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.group.RequestUI;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("editing/rooms")
@Validated
public class RoomController {
    private static final String ROOM = "room";
    private final ModelAndView mv = new ModelAndView();
    private final BaseService<Room> roomService;

    public RoomController(BaseService<Room> roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ModelAndView getAll() {
        mv.clear();
        mv.setViewName("editing/rooms/view");
        mv.addObject("rooms", roomService.getAll());
        return mv;
    }

    @GetMapping("add")
    public ModelAndView add() {
        mv.clear();
        mv.setViewName("editing/rooms/add");
        mv.addObject(ROOM, new Room());
        return mv;
    }

    @PostMapping("add")
    public ModelAndView add(@Validated(RequestUI.class) Room room, BindingResult result) {
        if (result.hasErrors()) {
            mv.clear();
            mv.setViewName("editing/rooms/add");
            mv.addObject(ROOM, room);
            return mv;
        } else {
            roomService.create(room);
            return getAll();
        }
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        mv.clear();
        mv.setViewName("editing/rooms/edit");
        mv.addObject(ROOM, roomService.getById(id));
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(@Validated(RequestUI.class) Room room, BindingResult result) {
        if (result.hasErrors()) {
            mv.clear();
            mv.setViewName("editing/rooms/edit");
            mv.addObject(ROOM, room);
            return mv;
        } else {
            roomService.update(room);
            return getAll();
        }
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        roomService.delete(roomService.getById(id));
        return getAll();
    }
}
