package net.runelite.client.plugins.slayermusiq;

import net.runelite.client.util.LinkBrowser;
import net.runelite.api.ChatMessageType;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;

public class QuestGuideLinks {
  private static final Link[] QUEST_GUIDE_LINKS = {
    new Link("Black Knights' Fortress", "https://www.youtube.com/watch?v=m778IRogFPg")
  };

  private static class Link {

    private String questName;
    private String url;

    public Link(String questName, String url) {
      this.questName = questName;
      this.url = url;
    }

    public String getQuestName() {
      return questName;
    }

    public void openURL() {
      LinkBrowser.browse(this.url);
    }

  }

  private static boolean openGuide(String questName) {
    for (Link link : QUEST_GUIDE_LINKS) {
      if (link.getQuestName().equals(questName)) {
        link.openURL();
        return true;
      }
    }
    return false;
  }

  private static void logQuestNotFoundError(String questName, ChatMessageManager chatMessageManager) {
    String chatMessage = new ChatMessageBuilder()
      .append(ChatColorType.NORMAL)
      .append("Could not find Slayermusiq1 guide for " + questName)
      .build();

    chatMessageManager.queue(QueuedMessage.builder()
      .type(ChatMessageType.GAME)
      .runeLiteFormattedMessage(chatMessage)
      .build());
  }

  public static void tryOpenGuide(String questName, ChatMessageManager chatMessageManager) {
    boolean success = openGuide(questName);
    if (!success) {
      logQuestNotFoundError(questName, chatMessageManager);
    }
  }
}
