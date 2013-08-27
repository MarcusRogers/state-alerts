package bg.statealerts.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import bg.statealerts.model.Document
import bg.statealerts.services.SearchService
import javax.inject.Inject
import scala.collection.JavaConversions._
import scala.collection.mutable.Buffer
import com.codahale.jerkson.Json
import javax.servlet.http.HttpServletResponse
import org.apache.commons.io.IOUtils
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.ui.Model

@Controller
class MainController {

  @Inject
  var searcher: SearchService = _

  @Inject
  var ctx: UserContext = _

  @RequestMapping(Array("/"))
  def index = {
    "layout:index"
  }

  @RequestMapping(Array("/search"))
  def search(@RequestParam keywords: String, model: Model): String = {
    val results = seqAsJavaList(searcher.search(keywords))
    model.addAttribute("results", results)
    "searchResults"
  }
  
  // serializing to JSON (using the built-in mechanisms doesn't work well)
  // val result = Json.generate(bufferAsJavaList(buffer))
  // IOUtils.write(result, response.getOutputStream())
}