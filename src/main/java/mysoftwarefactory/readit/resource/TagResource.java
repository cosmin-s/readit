package mysoftwarefactory.readit.resource;

import mysoftwarefactory.readit.dao.TagRepository;
import mysoftwarefactory.readit.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagResource
{
    @Autowired
    TagRepository tagRepository;

    @RequestMapping(method = RequestMethod.POST)
    private void create(@RequestBody Tag tag)
    {
        tagRepository.saveAndFlush(tag);
    }
}
