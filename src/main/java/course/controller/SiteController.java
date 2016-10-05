package course.controller;

/**
 * Created by Артем Константинович on 06.10.2016.
 */
/*@Controller
@RequestMapping("/")
public class SiteController {

    @Autowired
    private SiteRepository siteRepository;


    @Autowired
    public SiteController(SiteRepository siteRepository){
        this.siteRepository = siteRepository;
    }

    @RequestMapping(value = "/site/info/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Site ViewProfile(HttpSession httpSession, @PathVariable("id") String id){
        Site site = new Site();
        site = siteRepository.FindByOwnerId(Integer.parseInt(id));
        return null;
    }
}*/
