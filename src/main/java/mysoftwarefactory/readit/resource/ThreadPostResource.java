package mysoftwarefactory.readit.resource;

import mysoftwarefactory.readit.dao.TagRepository;
import mysoftwarefactory.readit.dao.ThreadPostRepository;
import mysoftwarefactory.readit.model.Tag;
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

    @Autowired
    TagRepository tagRepository;

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
        List<Tag> tags = threadPost.getTagList();
        for (Tag tag: tags)
        {
            List<Tag> persistedTag = tagRepository.findByName(tag.getName());
            if (persistedTag == null || persistedTag.isEmpty())
                tagRepository.save(tag);
            else
                tag.setId(persistedTag.get(0).getId());
        }
        threadPostRepository.saveAndFlush(threadPost);
    }
}
