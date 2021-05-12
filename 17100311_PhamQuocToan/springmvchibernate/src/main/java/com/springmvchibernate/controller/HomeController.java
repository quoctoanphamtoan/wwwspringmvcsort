package com.springmvchibernate.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvchibernate.dao.impl.CardDao;
import com.springmvchibernate.dao.impl.PersonDao;
import com.springmvchibernate.entity.CreditCard;
import com.springmvchibernate.entity.Person;

@Controller
@RequestMapping("/")
public class HomeController {
	private PersonDao peson = new PersonDao();
	private CardDao CardDao = new CardDao();

	
	@RequestMapping(value = "login",method = RequestMethod.GET)
	public String login() { 
		return "loginForm";
	}
	
	@RequestMapping(value = "home/{id}")
	public String index(ModelMap modelMap,@PathVariable("id") int id) {
		List<Person> listperson = peson.getAllPerson(id);
		modelMap.addAttribute("listperson", listperson);
		modelMap.addAttribute("idtemp" ,id);
		return "home";

	}

	@RequestMapping(value = "home/addperson", method = RequestMethod.GET)
	public String formAddPerson() {
		return "addPerson";

	}

	@RequestMapping(value = "home/addperson", method = RequestMethod.POST)
	public String save(@ModelAttribute("person") Person person) {
		peson.saveOrUpdate(person);
		return "redirect:/home/1";

	}

	@RequestMapping(path = "home/card/{id}", method = RequestMethod.GET)
	public String Card(@PathVariable("id") int id, ModelMap modelMap) {
		List<CreditCard> list = peson.getCardByPersonId(id);
		modelMap.addAttribute("listCard", list);
		modelMap.addAttribute("personId", id);
		return "card";
	}

	@RequestMapping(path = "home/card/home", method = RequestMethod.GET)
	public String backHome() {
		return "redirect:/home/1";
	}

	@RequestMapping(path = "home/card/addcard", method = RequestMethod.GET)
	public String formAddCard() {
		return "addCard";
	}

	@RequestMapping(path = "home/remove/{id}", method = RequestMethod.GET)
	public String RemovePerson(@PathVariable("id") int id) {
		peson.DeletePerson(id);
		return "redirect:/home/1";
	}

	@RequestMapping(value = "home/card/addcard/{personId}", method = RequestMethod.POST)
	public String addCard(@PathVariable("personId") int personId, @ModelAttribute("card") CreditCard card) {
		System.out.println(personId);
		System.out.println(card);
		System.out.println("loi");
		CardDao.addCard(personId, card);

		return "redirect:/home/card/" + personId;

	}

	@RequestMapping(value = "home/card/deletecard/{cardId}/{personId}", method = RequestMethod.GET)
	public String deleteCard(@PathVariable("cardId") int cardId, @PathVariable("personId") int personId) {
		CardDao.deleteCard(cardId);
		return "redirect:/home/card/" + personId;

	}

	@RequestMapping(value = "home/editPerson/{personId}", method = RequestMethod.GET)
	public String showFormEditPerson(@PathVariable("personId") int personId, ModelMap modelMap) {
		Person person = peson.getPersonById(personId);
		modelMap.addAttribute("personEdit", person);
		return "editPerson";

	}

	@RequestMapping(value = "home/editPerson/edit/{personId}", method = RequestMethod.POST)
	public String editPerson(@ModelAttribute("person") Person personEdit, @PathVariable("personId") int personId) {
		personEdit.setId(personId);
		peson.EditPerson(personEdit);
		return "redirect:/home/1";

	}

	@RequestMapping(value = "editPerson/home", method = RequestMethod.GET)
	public String backHomeEdit() {

		return "redirect:/home/1";

	}

	@RequestMapping(value = "home/card/editCard/{cardId}/{personId}", method = RequestMethod.GET)
	public String showFormEditCard(@PathVariable("cardId") int cardId, @PathVariable("personId") int personId,
			ModelMap modelMap) {
		modelMap.addAttribute("cardEdit", CardDao.getCardById(cardId));
		modelMap.addAttribute("personId", personId);

		return "editCard";

	}

	@RequestMapping(value = "home/card/edit/{cardId}/{personId}", method = RequestMethod.POST)
	public String editCard(@ModelAttribute("cardEdit") CreditCard cardEdit, @PathVariable("cardId") int cardId,
			@PathVariable("personId") int personId) {
		cardEdit.setId(cardId);
		CardDao.editCard(cardEdit);

		return "redirect:/home/card/" + personId;

	}

	@RequestMapping(value = "home/dangxuat",method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/home/1";
	}
	
//	dangxuat
//	card/editCard/23/edit/23/6
//	card/editCard/23
//	editCard/${item.getId()}
//	editPerson/home
//	card/deletecard/11
//	addcard/${personId}
//	editPerson/${item.getId()}

}
