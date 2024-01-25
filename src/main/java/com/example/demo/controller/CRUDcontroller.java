package com.example.demo.controller;

import java.util.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.CRUDmodels;


@RestController
public class CRUDcontroller {

	private List<CRUDmodels> crudModels = new ArrayList<>();
	
	//get data
		@GetMapping("/getData")
		public List<CRUDmodels> getAllCRUDModels(){
			return crudModels;
		}
		
		
	//post the data
		@PostMapping("/postData")
		public String createCrudModel(@RequestBody CRUDmodels crudModel )
		{
			crudModels.add(crudModel);
			return "Data Added";
		}
		
		
	//delete data
		@DeleteMapping("/{id}")
		public String deleteCRUDModel(@PathVariable Long  id) {
			CRUDmodels crudModel = findCRUDModelById(id);
			if(crudModel != null) {
				crudModels.remove(crudModel);
				return "Data Deleted Successfully";
			}
			else {
				return "Data Not Deleted"; 
			}
		}
		
	//find out the ID here
		private CRUDmodels findCRUDModelById(Long id) {
			return crudModels.stream().filter(
					crudModel -> crudModel.getId().equals(id)).findFirst().orElse(null);
					
		}	
		
		
	//update data
		@PutMapping("/{id}")
		public String updateCRUDModel(@PathVariable Long id, @RequestBody CRUDmodels updatecrudModel)
		{
			CRUDmodels exit = findCRUDModelById(id);
			
			if(exit != null) {
				exit.setTitle(updatecrudModel.getTitle());
				exit.setCompleted(updatecrudModel.isCompleted());
				return "update data successfully";
			}
			else {
				return "Not updated";
			}	
		
		
		}
}
