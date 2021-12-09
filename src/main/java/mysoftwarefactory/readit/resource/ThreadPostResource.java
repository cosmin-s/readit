package mysoftwarefactory.readit.resource;

import mysoftwarefactory.readit.dao.ThreadPostRepository;
import mysoftwarefactory.readit.model.ThreadPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/threadPost")
public class ThreadPostResource
{
    @Autowired
    ThreadPostRepository threadPostRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<ThreadPost> retrieveAll()
    {
        return threadPostRepository.findAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ThreadPost retrieve(@PathVariable Long id )
    {
        return threadPostRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody ThreadPost threadPost)
    {
        threadPostRepository.saveAndFlush(threadPost);
    }
}
