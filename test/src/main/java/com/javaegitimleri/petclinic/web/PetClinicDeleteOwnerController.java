package com.javaegitimleri.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.service.PetClinicService;

@Controller
public class PetClinicDeleteOwnerController {
	
	@Autowired
	PetClinicService petClinicService;
	
	@RequestMapping(value="/owners/delete/{id}",method=RequestMethod.GET)
	public String loadOwner(@PathVariable("id") Long id, ModelMap modelMap) {
		Owner owner = petClinicService.findOwner(id);
		modelMap.put("owner", owner);
		return "deleteOwner";
	}
	
	@RequestMapping(value="/owners/delete/{id}",method=RequestMethod.POST)
	public String loadOwner(@PathVariable("id") Long id) {
		petClinicService.deleteOwner(id);
		return "redirect:/owners";
	}
}