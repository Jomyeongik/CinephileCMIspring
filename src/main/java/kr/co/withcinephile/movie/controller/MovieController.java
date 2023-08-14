package kr.co.withcinephile.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MovieController {
	
	@RequestMapping(value="/movie/korea.do", method=RequestMethod.GET)
	public String showKoreaMovie() {
		return "movie/koreaMovie";
	}
	
	@RequestMapping(value="/movie/international.do", method=RequestMethod.GET)
	public String showInternationalMovie() {
		return "movie/internationalMovie";
	}

	@RequestMapping(value="/movie/detail.do", method=RequestMethod.GET)
	public String showMovieDetail() {
		return "movie/movieDetail";
	}
	
	@RequestMapping(value="/movie/info.do", method=RequestMethod.GET)
	public String showMovieReward() {
		return "movie/movieInfo";
	}

	
}
