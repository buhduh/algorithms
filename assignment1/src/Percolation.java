import edu.princeton.cs.algs4.Bag;

public class Percolation {

  private class Node {
    public Bag<Integer> indeces;
    private boolean open;

    Node(int index) {
      indeces = new Bag<Integer>();
      indeces.add(index);
      open = false;
    }

    public void open() {
      open = true;
    }

    public boolean isOpen() {
      return open;
    }
  }

  private Node[] nodes;
  private int N;
  private int numOpen;
  //virtual nodes
  private int topIndex;
  //virtual nodes
  private int bottomIndex;

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    N = n;
    int sq = N*N;
    numOpen = 0;
    nodes = new Node[sq + 2];
    for(int i = 0; i < sq; i++) {
      nodes[i] = new Node(i);
    }

    topIndex = sq;
    nodes[topIndex] = new Node(topIndex);
    nodes[topIndex].open();

    bottomIndex = sq + 1;
    nodes[bottomIndex] = new Node(bottomIndex);
    nodes[bottomIndex].open();
  }

  private int getIndex(int row, int col) {
    if(row < 1 || row > N || col < 1 || col > N) {
      throw new IllegalArgumentException();
    }
    return (row - 1) * N + (col - 1) % N;
  }

  //feels so weird not deleting the old node....
  private void join(int one, int two) {
    if(!nodes[one].isOpen() || !nodes[two].isOpen()) {
      return;
    }
    if(nodes[one] == nodes[two]) {
      return;
    }
    int lessIndex, moreIndex;
    if(nodes[one].indeces.size() < nodes[two].indeces.size()) {
      lessIndex = one;
      moreIndex = two;
    } else {
      lessIndex = two;
      moreIndex = one;
    }
    //don't know the behavior if I switch this out
    //from underneath the iteration or really care
    for(int i : nodes[lessIndex].indeces) {
      if(i == lessIndex) continue;
      nodes[i] = nodes[moreIndex];
      nodes[moreIndex].indeces.add(i);
    }
    nodes[lessIndex] = nodes[moreIndex];
    nodes[moreIndex].indeces.add(lessIndex);
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    int index = getIndex(row, col); 
    nodes[index].open();
    numOpen++;
    try {
      int left = getIndex(row, col - 1);
      join(index, left);
    } catch(IllegalArgumentException e) {}
    try {
      int right = getIndex(row, col + 1);
      join(index, right);
    } catch(IllegalArgumentException e) {}
    try {
      int up = getIndex(row - 1, col);
      join(index, up);
    } catch(IllegalArgumentException e) {
      if(row == 1) {
        join(index, topIndex);
      }
    }
    try {
      int down = getIndex(row + 1, col);
      join(index, down);
    } catch(IllegalArgumentException e) {
      if(row == N) {
        join(index, bottomIndex);
      }
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    int index = getIndex(row, col);
    return nodes[index].isOpen();
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    int index = getIndex(row, col);
    //index must be open if top and index are connected
    return nodes[index] == nodes[topIndex];
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return numOpen;
  }

  // does the system percolate?
  public boolean percolates() {
    return nodes[topIndex] == nodes[bottomIndex];
  }

}

