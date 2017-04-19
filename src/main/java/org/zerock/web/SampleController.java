package org.zerock.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.BoardVO;

@Controller
@RequestMapping("/sample/*")
public class SampleController {

	//url지정 2개, 지금쓰는건 책에 없는거
	@GetMapping({"/doA","/aaa"}) //이건 배열임.
	public void doA(Model model){ //void면 경로 찾음. model 담아서 파라미터 던지는거.
		System.out.println("doA");
		
		BoardVO vo = new BoardVO();
		vo.setBno(12);
		vo.setTitle("승규짱0");
		vo.setContent("하하하");
		
		model.addAttribute("vo", vo); // 모델파라미터는 맨 뒤에 줌.
		
	}
	
	@GetMapping("/doB")
	public String doB(){
		System.out.println("doB");
		return "redirect:/sample/doA";
	}
	
	@PostMapping("/doA")
	public void doAPost(BoardVO vo){
		
		System.out.println(vo);
		
	}
	
	
}
