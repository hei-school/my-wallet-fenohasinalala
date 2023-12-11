package model;

import model.enums.Size;
import utils.DisplayUtils;

public class IDPhoto extends Item {

  private String description;
  private Integer itemCount;

  public IDPhoto(String owner, String description, Integer itemCount) {
    super(owner, "OK", Size.SMALL);
    this.description = description;
    this.itemCount = itemCount;
  }

  @Override
  public void viewItem() {
    System.out.println("Type: ID Photo");
    System.out.println("Description: " + DisplayUtils.formatNullOrEmptyValue(description));

    System.out.println("Owner: " + DisplayUtils.formatNullOrEmptyValue(super.getOwner()));
    System.out.println("Status: " + DisplayUtils.formatNullOrEmptyValue(super.getStatus()));
    System.out.println("Added date: " + super.getAddedDate());
    System.out.println("--------------");
  }

  @Override
  public int getItemCount() {
    return itemCount;
  }
}
