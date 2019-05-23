package com.hellokoding.springboot;

/*
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }
}
*/
 
import java.util.ArrayList;
import java.util.List;
 
import com.hellokoding.ihm.PersonForm;
// import com.hellokoding.domaine.Person;
import com.hellokoding.ihm.PersonLine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

// pour utiliser l'interface avec la BD
import java.util.Date;
// import libinsa.txnscriptUtil ;
import com.hellokoding.persistance.txnscript;
// types retournés par les opération txnscript
import java.sql.ResultSet;

 
@Controller
public class HelloController {
 
	Logger logger = LoggerFactory.getLogger(HelloController.class);
	// txn : objet faisant le lien avec le SGBD
	txnscript txn = new txnscript() ;
	
    private static List<PersonLine> persons_table = new ArrayList<PersonLine>();

/*
    static {
        persons_table.add(new PersonLine("Bill", "Gates"));
        persons_table.add(new PersonLine("Steve", "Jobs"));
    }
*/
    // Injectez (inject) via application.properties.
    @Value("${welcome.message}")
    private String message;
 
    @Value("${error.message}")
    private String errorMessage;
 
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
 
        model.addAttribute("message", message);
 
        return "index";
    }
 
    @RequestMapping(value = { "/associesList" }, method = RequestMethod.GET)
    public String associesList(Model model) throws Exception {

		// c'est la premiere fois qu'on affiche => initialiser l'element qui représente l'ecran
		if ( persons_table.size() == 0 )
		{		
			ResultSet rs = txn.remonterEnrAssocies() ;
			
			// charger la liste qui va etre presentee a l'ecran
			logger.info( "associesList : rs initialized" ) ;
			while ( rs.next() )
			{
			 String firstname = rs.getString ( "firstname" ) ;
			 String surname = rs.getString ( "surname" ) ;

			 persons_table.add(new PersonLine(firstname, surname));

			 logger.info( "associesList firstname : " + firstname );
			 logger.info( "associesList surname : " + surname );
			}		
		}
 		model.addAttribute("persons_table", persons_table);

        return "associesList";
    }
 
    @RequestMapping(value = { "/addAssocie" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {
 
        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);
 
        return "addAssocie";
    }
 
    @RequestMapping(value = { "/addAssocie" }, method = RequestMethod.POST)
    public String savePerson(Model model, //
            @ModelAttribute("personForm") PersonForm personForm) {
 
        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();
 
        if (firstName != null && firstName.length() > 0 //
                && lastName != null && lastName.length() > 0) {
            PersonLine newPersonLine = new PersonLine(firstName, lastName);
            persons_table.add(newPersonLine);
 
            return "redirect:/associesList";
        }
 
        model.addAttribute("errorMessage", errorMessage);
        return "addAssocie";
    }
 
}