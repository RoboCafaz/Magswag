package net.maguuma.magswag.dataloader;

import java.io.IOException;
import java.util.List;
import net.maguuma.magswag.common.datatypes.items.Item;
import net.maguuma.magswag.dataloader.json.DataSaver;
import net.maguuma.magswag.dataloader.json.ItemFactory;
import com.google.gson.Gson;

public class DataRunner {
  public static void main(String... args) throws IOException {
    List<Item> items = ItemFactory.createItems();
    Gson gson = new Gson();
    String contents = gson.toJson(items);
    String results = DataSaver.saveData("Items.json", contents);
    System.out.println("Data saved to " + results);
  }
}
