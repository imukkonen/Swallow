package hh.palvelinohj.swallow.web;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.palvelinohj.swallow.domain.Dancegroup;
import hh.palvelinohj.swallow.domain.DancegroupRepository;
import hh.palvelinohj.swallow.domain.Instructor;
import hh.palvelinohj.swallow.domain.InstructorRepository;
import hh.palvelinohj.swallow.domain.Place;
import hh.palvelinohj.swallow.domain.PlaceRepository;
import hh.palvelinohj.swallow.domain.User;
import hh.palvelinohj.swallow.domain.UserRepository;

@Controller
public class DancegroupController {

	@Autowired
	private DancegroupRepository drepository; 
	
	@Autowired
	private PlaceRepository prepository; 
	
	@Autowired
	private InstructorRepository irepository; 
	
	@Autowired
	private UserRepository urepository;
	
	//index
		@RequestMapping(value="/index")
	    public String index() {	
	        return "index";
	    }
		
		//login
		@RequestMapping(value="/login")
	    public String login() {	
	        return "login";
	    }
		
				
		//kaikki ryhmät
		@RequestMapping(value="/grouplist")
	    public String groupList(Model model) {	
	        model.addAttribute("groups", drepository.findAll());
	        return "grouplist";
	    }
		
		@RequestMapping(value = "/add")
		@PreAuthorize("hasAuthority('ADMIN')")
	    public String addGroup(Model model){
	    	model.addAttribute("group", new Dancegroup());
	    	model.addAttribute("places", prepository.findAll());
	    	model.addAttribute("instructors", irepository.findAll());
	        return "addgroup";
	    }
		
		@RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String save(Dancegroup group){
	        drepository.save(group);
	        return "redirect:grouplist";
	    }
		
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	    public String deleteGroup(@PathVariable("id") Long dancegroupId, Model model) {
	    	drepository.deleteById(dancegroupId);
	        return "redirect:../grouplist";
	    }   
		
		@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	    public String editGroup(@PathVariable("id") Long dancegroupId, Model model) {
	    	model.addAttribute("group", drepository.findById(dancegroupId));
	    	Dancegroup group=this.drepository.findById(dancegroupId).get();
	    	model.addAttribute("place", group.getPlace().getPlaceId());
	    	model.addAttribute("places", prepository.findAll());
	    	model.addAttribute("instr",group.getInstructor().getInstructorId());
	    	model.addAttribute("instructors", irepository.findAll());
	    	return "editgroup";
	    }
		
		@RequestMapping(value = "/groupusers/{id}", method = RequestMethod.GET)
	    public String groupUsers(@PathVariable("id") Long dancegroupId, Model model) {
	    	model.addAttribute("group", drepository.findById(dancegroupId).get());
	    	Dancegroup group=this.drepository.findById(dancegroupId).get();
	    	model.addAttribute("users", group.getUsers());
	    	return "groupuserlist";
	    }
		
		@RequestMapping(value ="/userinfo", method=RequestMethod.GET)
		public String findUserInfo(Model model, Principal principal) {
			String name=principal.getName();
			User user1 =urepository.findByUsername(name);
			model.addAttribute("userinfo", user1.toString());
			return "user";
		}
		
//-------------harjoituspaikan osa ----------------------------//
	
		//kaikki harjoituspaikat
		@RequestMapping(value="/placelist")
	    public String placeList(Model model) {	
	        model.addAttribute("places", prepository.findAll());
	        return "placelist";
	    }
		
		// harjoituspaikan ryhmät
		
		@RequestMapping(value = "/placegroups/{id}", method = RequestMethod.GET)
	    public String searchGroup(@PathVariable("id") Long placeId, Model model) {
	    	model.addAttribute("place", prepository.findById(placeId).get());
	    	Place place=this.prepository.findById(placeId).get();
	    	model.addAttribute("groups", place.getGroups());
	    	return "placegrouplist";
	    }
		
		@RequestMapping(value = "/addplace")
	    public String addplace(Model model){
	    	model.addAttribute("place", new Place());
	    	return "addplace";
	    }
		
		@RequestMapping(value = "/saveplace", method = RequestMethod.POST)
	    public String saveplace(Place place){
	        prepository.save(place);
	        return "redirect:placelist";
	    }
		
		@RequestMapping(value = "/deleteplace/{id}", method = RequestMethod.GET)
	    public String deletePlace(@PathVariable("id") Long placeId, Model model) {
	    	prepository.deleteById(placeId);
	        return "redirect:../placelist";
	    }   
		
		@RequestMapping(value = "/editplace/{id}", method = RequestMethod.GET)
	    public String editPlace(@PathVariable("id") Long placeId, Model model) {
	    	model.addAttribute("place", prepository.findById(placeId).get());
	    	return "editplace";
	    }
		
	// ------------- Ohjaajan osa ---------------------//
		
		//kaikki ohjaajat
				@RequestMapping(value="/instructorlist")
			    public String instructorList(Model model) {	
			        model.addAttribute("instructors", irepository.findAll());
			        return "instructorlist";
			    }
			
				// ohjaajan ohjattamat ryhmät
				
				@RequestMapping(value = "/instructorgroups/{id}", method = RequestMethod.GET)
			    public String instructorGroup(@PathVariable("id") Long instructorId, Model model) {
			    	model.addAttribute("instructor", irepository.findById(instructorId).get());
			    	Instructor instructor=this.irepository.findById(instructorId).get();
			    	model.addAttribute("groups", instructor.getGroups());
			    	return "instructorgrouplist";
			    }
				
					
				@RequestMapping(value = "/addinstructor")
			    public String addinstructor(Model model){
			    	model.addAttribute("instructor", new Instructor());
			    	return "addinstructor";
			    }
				
				
				@RequestMapping(value = "/saveinstructor", method = RequestMethod.POST)
			    public String saveInstructor(Instructor instructor){
			        irepository.save(instructor);
			        return "redirect:instructorlist";
			    }
			
				@RequestMapping(value = "/deleteinstructor/{id}", method = RequestMethod.GET)
			    public String deleteInstrictor(@PathVariable("id") Long instructorId, Model model) {
			    	irepository.deleteById(instructorId);
			        return "redirect:../instructorlist";
			    }   
					
				@RequestMapping(value = "/editinstructor/{id}", method = RequestMethod.GET)
			    public String editInstructor(@PathVariable("id") Long instructorId, Model model) {
			    	model.addAttribute("instructor", irepository.findById(instructorId).get());
			    	return "editinstructor";
			    }
	
		
		
	//---------------------------- RESTful servicies ------------------------------//	
		// RESTful service kaikki ryhmät
	    @RequestMapping(value="/groups", method = RequestMethod.GET)
	    public @ResponseBody List<Dancegroup> groupListRest() {	
	        return (List<Dancegroup>) drepository.findAll();
	    }    
		
	 // RESTful service löytä ryhmä id-tunnuksella
	    @RequestMapping(value="/group/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Dancegroup> findGroupRest(@PathVariable("id") Long dancegroupId) {	
	    	return drepository.findById(dancegroupId);
	    }  

		// RESTful service kaikki harjoituspaikat
	    @RequestMapping(value="/places", method = RequestMethod.GET)
	    public @ResponseBody List<Place> placeListRest() {	
	        return (List<Place>) prepository.findAll();
	    }    
		
	 // RESTful service löytä harjoituspaikan id-tunnuksella
	    @RequestMapping(value="/place/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Place> findPlaceRest(@PathVariable("id") Long placeId) {	
	    	return prepository.findById(placeId);
	    } 

		// RESTful service kaikki ohjaajat
	    @RequestMapping(value="/instructors", method = RequestMethod.GET)
	    public @ResponseBody List<Instructor> instructorListRest() {	
	        return (List<Instructor>) irepository.findAll();
	    }    
		
	 // RESTful service löytä ohjaajan id-tunnuksella
	    @RequestMapping(value="/instructor/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Instructor> findBookRest(@PathVariable("id") Long instructorId) {	
	    	return irepository.findById(instructorId);
	    } 
}
