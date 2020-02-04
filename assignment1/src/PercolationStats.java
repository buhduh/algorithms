import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

  private double[] percThreshList;
  private int T;

  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if(n <= 0 || trials <= 0) {
      throw new IllegalArgumentException();
    }
    T = trials;
    percThreshList = new double[trials];
    for(int i = 0; i < trials; i++) {
      Percolation perc = new Percolation(n);
      int sq = n*n;
      double count = 0;
      int[] order = StdRandom.permutation(sq);
      for(Integer loc : order) {
        count++;
        int row = loc / n;
        int col = loc % n;
        perc.open(row + 1, col + 1);
        if(perc.percolates()) {
          percThreshList[i] = count / (double) sq;
          break;
        }
      }
    }
  }

  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(percThreshList);
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return StdStats.stddev(percThreshList);   
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean() - ((1.96*stddev()) / Math.sqrt((double) T));
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + ((1.96*stddev()) / Math.sqrt((double) T));
  }

  private void printIt() {
    //don't care enough to fuff about with printf
    System.out.println("mean                    = " + Double.toString(mean()));
    System.out.println("stddev                  = " + Double.toString(stddev()));
    System.out.println("95% confidence interval = [" + Double.toString(confidenceLo()) + ", " + Double.toString(confidenceLo()) + "]");

  }

  // test client (see below)
  public static void main(String[] args) {
    PercolationStats percs = 
      new PercolationStats(new Integer(args[0]), new Integer(args[1]));
    System.out.println("mean                    = " + Double.toString(percs.mean()));
    System.out.println("stddev                  = " + Double.toString(percs.stddev()));
    System.out.println("95% confidence interval = [" + Double.toString(percs.confidenceLo()) + ", " + Double.toString(percs.confidenceLo()) + "]");
  }

}
