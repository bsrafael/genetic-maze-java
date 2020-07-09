public class Maze {
  static String UP = "up";
  static String RIGHT = "right";
  static String DOWN = "down";
  static String LEFT = "left";

  Cell[][] cells = new Cell[4][];

  public Maze() {
    Cell[] row0 = {
      new Cell(0, new String[]{UP, RIGHT}, false),
      new Cell(1, new String[]{RIGHT, LEFT}, false),
      new Cell(2, new String[]{UP, RIGHT, LEFT}, false),
      new Cell(3, new String[]{UP, LEFT}, false)
    };
    Cell[] row1 = {
      new Cell(4, new String[]{UP, RIGHT, DOWN}, false),
      new Cell(5, new String[]{UP, LEFT}, false),
      new Cell(6, new String[]{UP, DOWN}, false),
      new Cell(7, new String[]{DOWN}, false)
    };
    Cell[] row2 = {
      new Cell(8, new String[]{UP, RIGHT, DOWN}, false),
      new Cell(9, new String[]{DOWN, LEFT}, false),
      new Cell(10, new String[]{UP, RIGHT, DOWN}, false),
      new Cell(11, new String[]{UP, LEFT}, false)
    };
    Cell[] row3 = {
      new Cell(12, new String[]{RIGHT, DOWN}, false),
      new Cell(13, new String[]{RIGHT, LEFT}, false),
      new Cell(14, new String[]{RIGHT, DOWN, LEFT}, false),
      new Cell(15, new String[]{DOWN, LEFT}, true)
    };

    this.cells[0] = row0;
    this.cells[1] = row1;
    this.cells[2] = row2;
    this.cells[3] = row3;
  }

   class Cell {
    int id = -1;
    String[] actions = null;
    Boolean exit = false;

    public Cell(int id, String[] actions, Boolean exit) {
      this.id = id;
      this.actions = actions;
      this.exit = exit;
    }
  }
  
}