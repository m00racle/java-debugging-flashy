package com.teamtreehouse.flashy.controllers;

import com.teamtreehouse.flashy.domain.FlashCard;
import com.teamtreehouse.flashy.services.FlashCardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
  /** Debugging Existing Java Apps:
   * 3-2: this is the constant resulting from Refactor>Extract>Constant*/
  public static final int AMOUNT_TO_SHOW = 3;
  private FlashCardService flashCardService;

  @Autowired
  public void setFlashCardService(FlashCardService flashCardService) {
    this.flashCardService = flashCardService;
  }

  /** Debugging Existing Java Apps:
   * 3-1: we need to change this to three but we're making it a constant!;
   * */
  @RequestMapping("/")
  public String index(Model model) {

    StringBuilder ctaBuilder = new StringBuilder();
       //3-1: this need to make int refactor>extract>constant:
    List<FlashCard> cards = flashCardService.getRandomFlashCards(AMOUNT_TO_SHOW);
    ctaBuilder.append("Refresh your memory about ");
    for (FlashCard card : cards) {
      ctaBuilder.append(card.getTerm());
      if (card != cards.get(cards.size() - 1)) {
        ctaBuilder.append(", ");
      }
    }

    Long totalCount = flashCardService.getCurrentCount();
    if(totalCount>AMOUNT_TO_SHOW){
      //3-3:this is the code that count how many left are the flash card showed:

      //*it needs to be fixed by make the count answer after substracting with the number amount_to_show

      //*there are no such validation regrading what if the totalCount is bigger than AMOUNT_TO_SHOW
      //WE NEED TO FIX THIS:
      ctaBuilder.append(" and ");
      ctaBuilder.append(totalCount - AMOUNT_TO_SHOW);
      ctaBuilder.append(" more");
    }

    model.addAttribute("cta", ctaBuilder.toString());
    model.addAttribute("flashCardCount", totalCount);
    return "index";
  }

}
