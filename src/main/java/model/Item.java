package model;

import java.time.Instant;
import java.util.UUID;
import model.enums.Size;

public abstract class Item {

  private String id;
  private String owner;
  private String status;
  private Size size;
  private Instant addedDate;

  public Item(String owner, String status, Size size) {
    this.id = UUID.randomUUID().toString();
    this.owner = owner != null && !owner.isEmpty() ? owner : "me";
    this.status = status;
    this.addedDate = Instant.now();
    this.size = size;
  }

  public String getId() {
    return id;
  }

  public String getOwner() {
    return owner;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Size getSize() {
    return size;
  }

  public void setSize(Size size) {
    this.size = size;
  }

  public Instant getAddedDate() {
    return addedDate;
  }

  public void setAddedDate(Instant addedDate) {
    this.addedDate = addedDate;
  }

  public abstract void viewItem();

  public abstract int getItemCount();
}
