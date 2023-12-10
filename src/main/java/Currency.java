public enum Currency {
  MGA(100, 200, 500, 1000, 2000, 5000, 10000, 20000),
  USD(1, 2, 5, 10, 20, 50, 100);


  private final double[] values;

  Currency(double... values) {
    this.values = values;
  }

  public double[] getValues() {
    return values;
  }

}
