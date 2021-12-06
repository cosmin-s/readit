package mysoftwarefactory.readit.resource;

import mysoftwarefactory.readit.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import mysoftwarefactory.readit.dao.PostRepository;

@RestController
@RequestMapping("/post")
public class PostResource {
	
	@Autowired
	PostRepository postRepository;
	
	/*@RequestMapping(method=RequestMethod.GET)
	public List<Note> getNotesByTag(@RequestParam String tag){
		return jpaNoteRepository.getNotesByTag(tag);
	}
	*/
	@RequestMapping(method= RequestMethod.POST)
	public void createNote(@RequestBody Post note){
		postRepository.saveAndFlush(note);
	}

}
