package com.skilldistillery.film.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.DatabaseAccessor;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	
	@Autowired
	private DatabaseAccessor dao;
	
	@RequestMapping( path = { "/", "home.do" } )
	public String home( Model model) {
		return "WEB-INF/home.jsp";
	}
	
	@RequestMapping( path = "GetFilmById.do", method = RequestMethod.GET, params = "id")
	public ModelAndView getFilmById(int id) {
		ModelAndView mv = new ModelAndView();
		Film film = dao.findFilmById(id);
		mv.setViewName("WEB-INF/singleFilm.jsp");
		mv.addObject("film", film);
		return mv;
	}

	@RequestMapping( path = "GetFilmByKeyword.do", method = RequestMethod.GET, params = "keyword")
	public ModelAndView getFilmById(String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> filmList = dao.findFilmByKeyword(keyword);
		mv.setViewName("WEB-INF/multipleFilms.jsp");
		mv.addObject("films", filmList);
		return mv;
	}
	
//	@RequestMapping("GetNumbers.do")
//	public ModelAndView getNumbers(@RequestParam("howmany") int count) {
//		hopper.reset();
//
//		List<String> nums = new ArrayList<>();
//		for (int i = 0; i < count; i++) {
//			nums.add(hopper.drawBall().getValue());
//		}
//
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("WEB-INF/form.jsp");
//		mv.addObject("listOfNumbers", nums);
//		return mv;
//	}

}
