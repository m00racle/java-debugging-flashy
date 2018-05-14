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
       //3-1:
    List<FlashCard> cards = flashCardService.getRandomFlashCards(AMOUNT_TO_SHOW);
    ctaBuilder.append("Refresh your memory about ");
    for (FlashCard card : cards) {
      ctaBuilder.append(card.getTerm());
      if (card != cards.get(cards.size() - 1)) {
        ctaBuilder.append(", ");
      }
    }
    ctaBuilder.append(" and ");
    Long totalCount = flashCardService.getCurrentCount();
    ctaBuilder.append(totalCount);
    ctaBuilder.append(" more");
    model.addAttribute("cta", ctaBuilder.toString());
    model.addAttribute("flashCardCount", totalCount);
    return "index";
  }

}
