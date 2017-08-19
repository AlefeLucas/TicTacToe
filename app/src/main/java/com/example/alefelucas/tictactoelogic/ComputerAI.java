package com.example.alefelucas.tictactoelogic;

/**
 * Created by Alefe Lucas on 20/11/2015.
 */


import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

public class ComputerAI {

    private static Random random = new Random();
    private static Slot which = Slot.X;

    public static int[] play(int level, Slot[][] board) {
        System.out.println("AI jogando...");
        System.out.println(level);
        which = Slot.X;
        switch (level) {
            case 1:
                return easy(board);

            case 2:
                return medium(board);

            case 3:
                return hard(board);

            case 4:
                return impossible(board);
            default:
                int[] error = {-1, -1};
                return error;
        }
    }


    private static int[] easy(Slot[][] board) {
        boolean done = false;
        int[] places = new int[2];
        int row = -1;
        int column = -1;
        while (!done) {
            row = random.nextInt(3);
            column = random.nextInt(3);
            if (board[row][column] == Slot.EMPTY) {
                done = true;
            }
        }

        places[0] = row;
        places[1] = column;

        return places;
    }

    private static int[] medium(Slot[][] board) {
        int[] places = new int[2];
        which = Slot.O;
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < board.length; x++) {
                if (board[x][0] == board[x][1] && board[x][0] == which && board[x][2] == Slot.EMPTY) {
                    places[0] = x;
                    places[1] = 2;
                    return places;
                } else if (board[x][2] == board[x][1] && board[x][2] == which && board[x][0] == Slot.EMPTY) {
                    places[0] = x;
                    places[1] = 0;
                    return places;
                } else if (board[x][0] == board[x][2] && board[x][0] == which && board[x][1] == Slot.EMPTY) {
                    places[0] = x;
                    places[1] = 1;
                    return places;
                } else if (board[0][x] == board[1][x] && board[0][x] == which && board[2][x] == Slot.EMPTY) {
                    places[0] = 2;
                    places[1] = x;
                    return places;
                } else if (board[2][x] == board[1][x] && board[2][x] == which && board[0][x] == Slot.EMPTY) {
                    places[0] = 0;
                    places[1] = x;
                    return places;
                } else if (board[0][x] == board[2][x] && board[0][x] == which && board[1][x] == Slot.EMPTY) {
                    places[0] = 1;
                    places[1] = x;
                    return places;
                }
            }
            int x = 0;
            if (board[x][x] == board[x + 1][x + 1] && board[x][x] == which && board[x + 2][x + 2] == Slot.EMPTY) {
                places[0] = x + 2;
                places[1] = x + 2;
                return places;
            } else if (board[x + 2][x + 2] == board[x + 1][x + 1] && board[x + 2][x + 2] == which && board[x][x] == Slot.EMPTY) {
                places[0] = x;
                places[1] = x;
                return places;
            } else if (board[x][x] == board[x + 2][x + 2] && board[x][x] == which && board[x + 1][x + 1] == Slot.EMPTY) {
                places[0] = x + 1;
                places[1] = x + 1;
                return places;
            } else if (board[x][x + 2] == board[x + 1][x + 1] && board[x][x + 2] == which && board[x + 2][x] == Slot.EMPTY) {
                places[0] = x + 2;
                places[1] = x;
                return places;
            } else if (board[x + 2][x] == board[x + 1][x + 1] && board[x + 2][x] == which && board[x][x + 2] == Slot.EMPTY) {
                places[0] = x;
                places[1] = x + 2;
                return places;
            } else if (board[x + 2][x] == board[x][x + 2] && board[x + 2][x] == which && board[x + 1][x + 1] == Slot.EMPTY) {
                places[0] = x + 1;
                places[1] = x + 1;
                return places;
            }

            which = Slot.X;
        }
        which = Slot.X;
        System.out.println("ENTRARÁ NO FACIL");
        return easy(board);

    }

    private static int[] hard(Slot[][] board) {
        int[] places = new int[2];

        ArrayList<Slot> slots = new ArrayList<Slot>();
        for (Slot rows[] : board) {
            for (Slot slot : rows) {
                slots.add(slot);
            }
        }

        if (!(slots.contains(Slot.X) || slots.contains(Slot.O))) {
            int x = random.nextInt(5);
            switch (x) {
                case 0:
                    places[0] = 1;
                    places[1] = 1;
                    break;
                case 1:
                    places[0] = 0;
                    places[1] = 0;
                    break;
                case 2:
                    places[0] = 2;
                    places[1] = 2;
                    break;
                case 3:
                    places[0] = 2;
                    places[1] = 0;
                    break;
                case 4:
                    places[0] = 0;
                    places[1] = 2;
                    break;

            }
            return places;
        } else {
            while (slots.contains(Slot.EMPTY)) {
                slots.remove(Slot.EMPTY);
            }
            if (slots.size() == 1) {
                if (board[1][1] == Slot.EMPTY) {
                    places[0] = 1;
                    places[1] = 1;
                    return places;
                } else {
                    int x = random.nextInt(4);
                    switch (x) {
                        case 1:
                            places[0] = 0;
                            places[1] = 0;
                            break;
                        case 2:
                            places[0] = 2;
                            places[1] = 2;
                            break;
                        case 3:
                            places[0] = 2;
                            places[1] = 0;
                            break;
                        case 0:
                            places[0] = 0;
                            places[1] = 2;
                            break;
                    }
                    return places;
                }
            } else {
                System.out.println("ENTRARÁ NO MÉDIO");
                return medium(board);
            }

        }
    }

    private static int[] impossible(Slot[][] board) {
        System.out.println("ENTROU NO IMPOSSIVEL");
        int[] places = new int[2];
        Slot same = Slot.O;
        Slot notSame = Slot.X;

        ArrayList<Slot> slots = new ArrayList<Slot>();
        for (Slot rows[] : board) {
            for (Slot slot : rows) {
                slots.add(slot);
            }
        }

        while (slots.contains(Slot.EMPTY)) {
            slots.remove(Slot.EMPTY);
        }

        if (slots.size() == 2) {
            if (board[1][1] == same) {
                if (board[0][0] == notSame) {
                    places[0] = 2;
                    places[1] = 2;
                    return places;
                } else if (board[2][2] == notSame) {
                    places[0] = 0;
                    places[1] = 0;
                    return places;
                } else if (board[0][2] == notSame) {
                    places[0] = 2;
                    places[1] = 0;
                    return places;
                } else if (board[2][0] == notSame) {
                    places[0] = 0;
                    places[1] = 2;
                    return places;
                } else if (board[0][1] == notSame) {
                    places[0] = 2;
                    places[1] = 0;
                    return places;
                } else if (board[1][0] == notSame) {
                    places[0] = 0;
                    places[1] = 2;
                    return places;
                } else if (board[2][1] == notSame) {
                    places[0] = 0;
                    places[1] = 0;
                    return places;
                } else if (board[1][2] == notSame) {
                    places[0] = 0;
                    places[1] = 0;
                    return places;
                }
            } else if (board[1][1] == notSame) {
                if (board[0][0] == same) {
                    places[0] = 2;
                    places[1] = 2;
                    return places;
                } else if (board[2][2] == same) {
                    places[0] = 0;
                    places[1] = 0;
                    return places;
                } else if (board[0][2] == same) {
                    places[0] = 2;
                    places[1] = 0;
                    return places;
                } else if (board[2][0] == same) {
                    places[0] = 0;
                    places[1] = 2;
                    return places;
                } else if (board[0][1] == same) {
                    places[0] = 2;
                    places[1] = 2;
                    return places;
                } else if (board[1][0] == same) {
                    places[0] = 0;
                    places[1] = 2;
                    return places;
                } else if (board[2][1] == same) {
                    places[0] = 0;
                    places[1] = 0;
                    return places;
                } else if (board[1][2] == same) {
                    places[0] = 0;
                    places[1] = 0;
                    return places;
                }
            } else if (board[0][1] == notSame && board[2][2] == same) {
                places[0] = 2;
                places[1] = 0;
                return places;
            } else if (board[1][2] == notSame && board[0][0] == same) {
                places[0] = 2;
                places[1] = 0;
                return places;
            } else if (board[0][1] == notSame && board[2][0] == same) {
                places[0] = 2;
                places[1] = 2;
                return places;
            } else if (board[1][0] == notSame && board[0][2] == same) {
                places[0] = 2;
                places[1] = 2;
                return places;
            } else if (board[1][2] == notSame && board[2][0] == same) {
                places[0] = 0;
                places[1] = 0;
                return places;
            } else if (board[2][1] == notSame && board[0][2] == same) {
                places[0] = 0;
                places[1] = 0;
                return places;
            } else if (board[2][1] == notSame && board[0][0] == same) {
                places[0] = 0;
                places[1] = 2;
                return places;
            } else if (board[1][0] == notSame && board[2][2] == same) {
                places[0] = 0;
                places[1] = 2;
                return places;
            } else if (board[2][2] == notSame && board[2][0] == same) {
                places[0] = 0;
                places[1] = 2;
                return places;
            } else if (board[2][0] == notSame && board[0][0] == same) {
                places[0] = 2;
                places[1] = 2;
                return places;
            } else if (board[0][0] == notSame && board[0][2] == same) {
                places[0] = 2;
                places[1] = 0;
                return places;
            } else if (board[0][2] == notSame && board[2][2] == same) {
                places[0] = 0;
                places[1] = 0;
                return places;
            } else if (board[2][1] == notSame && board[2][2] == same) {
                places[0] = 0;
                places[1] = 2;
                return places;
            } else if (board[1][0] == notSame && board[2][0] == same) {
                places[0] = 2;
                places[1] = 2;
                return places;
            } else if (board[1][2] == notSame && board[0][2] == same) {
                places[0] = 0;
                places[1] = 0;
                return places;
            } else if (board[0][1] == notSame && board[0][0] == same) {
                places[0] = 2;
                places[1] = 0;
                return places;
            } else if (board[0][0] == notSame && board[2][0] == same) {
                places[0] = 0;
                places[1] = 2;
                return places;
            } else if (board[0][2] == notSame && board[0][0] == same) {
                places[0] = 2;
                places[1] = 2;
                return places;
            } else if (board[2][0] == notSame && board[2][2] == same) {
                places[0] = 0;
                places[1] = 0;
                return places;
            } else if (board[2][2] == notSame && board[0][2] == same) {
                places[0] = 2;
                places[1] = 0;
                return places;
            } else if (board[0][1] == notSame && board[0][2] == same) {
                places[0] = 2;
                places[1] = 2;
                return places;
            } else if (board[1][0] == notSame && board[0][0] == same) {
                places[0] = 0;
                places[1] = 2;
                return places;
            } else if (board[1][2] == notSame && board[2][2] == same) {
                places[0] = 2;
                places[1] = 0;
                return places;
            } else if (board[2][1] == notSame && board[2][0] == same) {
                places[0] = 0;
                places[1] = 0;
                return places;
            } else if (board[0][0] == same && board[2][2] == notSame) {
                int x = random.nextInt(2);
                switch (x) {
                    case 1:
                        places[0] = 2;
                        places[1] = 0;
                        break;
                    case 0:
                        places[0] = 0;
                        places[1] = 2;
                        break;
                }
                return places;
            } else if (board[0][2] == same && board[2][0] == notSame) {
                int x = random.nextInt(2);
                switch (x) {
                    case 1:
                        places[0] = 0;
                        places[1] = 0;
                        break;
                    case 0:
                        places[0] = 2;
                        places[1] = 2;
                        break;
                }
                return places;
            } else if (board[2][2] == same && board[0][0] == notSame) {
                int x = random.nextInt(2);
                switch (x) {
                    case 1:
                        places[0] = 2;
                        places[1] = 0;
                        break;
                    case 0:
                        places[0] = 0;
                        places[1] = 2;
                        break;
                }
                return places;
            } else if (board[2][0] == same && board[0][2] == notSame) {
                int x = random.nextInt(2);
                switch (x) {
                    case 1:
                        places[0] = 0;
                        places[1] = 0;
                        break;
                    case 0:
                        places[0] = 2;
                        places[1] = 2;
                        break;
                }
                return places;
            }
        } else if (slots.size() == 3) {
            if (board[1][1] == same) {
                if ((board[0][0] == board[2][2] && board[0][0] == notSame)
                        || (board[2][0] == board[0][2] && board[2][0] == notSame)) {
                    int x = random.nextInt(4);
                    switch (x) {
                        case 1:
                            places[0] = 0;
                            places[1] = 1;
                            break;
                        case 2:
                            places[0] = 1;
                            places[1] = 0;
                            break;
                        case 3:
                            places[0] = 2;
                            places[1] = 1;
                            break;
                        case 0:
                            places[0] = 1;
                            places[1] = 2;
                            break;
                    }
                    return places;
                } else if (board[2][1] == notSame && board[0][0] == notSame) {
                    int x = random.nextInt(3);
                    switch (x) {
                        case 1:
                            places[0] = 2;
                            places[1] = 2;
                            break;
                        case 2:
                            places[0] = 2;
                            places[1] = 0;
                            break;
                        case 0:
                            places[0] = 1;
                            places[1] = 2;
                            break;
                    }
                    return places;
                } else if (board[0][1] == notSame && board[2][2] == notSame) {
                    int x = random.nextInt(3);
                    switch (x) {
                        case 1:
                            places[0] = 0;
                            places[1] = 0;
                            break;
                        case 2:
                            places[0] = 1;
                            places[1] = 0;
                            break;
                        case 0:
                            places[0] = 0;
                            places[1] = 2;
                            break;
                    }
                    return places;
                } else if (board[2][0] == notSame && board[1][2] == notSame) {
                    int x = random.nextInt(3);
                    switch (x) {
                        case 1:
                            places[0] = 0;
                            places[1] = 1;
                            break;
                        case 2:
                            places[0] = 0;
                            places[1] = 2;
                            break;
                        case 0:
                            places[0] = 2;
                            places[1] = 2;
                            break;
                    }
                    return places;
                } else if (board[1][0] == notSame && board[0][2] == notSame) {
                    int x = random.nextInt(3);
                    switch (x) {
                        case 1:
                            places[0] = 0;
                            places[1] = 0;
                            break;
                        case 2:
                            places[0] = 2;
                            places[1] = 1;
                            break;
                        case 0:
                            places[0] = 2;
                            places[1] = 0;
                            break;
                    }
                    return places;
                } else if (board[1][0] == notSame && board[2][2] == notSame) {
                    int x = random.nextInt(3);
                    switch (x) {
                        case 1:
                            places[0] = 0;
                            places[1] = 0;
                            break;
                        case 2:
                            places[0] = 0;
                            places[1] = 1;
                            break;
                        case 0:
                            places[0] = 2;
                            places[1] = 0;
                            break;
                    }
                    return places;
                } else if (board[0][1] == notSame && board[2][0] == notSame) {
                    int x = random.nextInt(3);
                    switch (x) {
                        case 1:
                            places[0] = 0;
                            places[1] = 0;
                            break;
                        case 2:
                            places[0] = 1;
                            places[1] = 2;
                            break;
                        case 0:
                            places[0] = 0;
                            places[1] = 2;
                            break;
                    }
                    return places;
                } else if (board[1][2] == notSame && board[0][0] == notSame) {
                    int x = random.nextInt(3);
                    switch (x) {
                        case 1:
                            places[0] = 0;
                            places[1] = 2;
                            break;
                        case 2:
                            places[0] = 2;
                            places[1] = 2;
                            break;
                        case 0:
                            places[0] = 2;
                            places[1] = 1;
                            break;
                    }
                    return places;
                } else if (board[2][1] == notSame && board[0][2] == notSame) {
                    int x = random.nextInt(3);
                    switch (x) {
                        case 1:
                            places[0] = 1;
                            places[1] = 0;
                            break;
                        case 2:
                            places[0] = 2;
                            places[1] = 0;
                            break;
                        case 0:
                            places[0] = 2;
                            places[1] = 2;
                            break;
                    }
                    return places;
                } else if (board[0][1] == notSame && board[1][2] == board[0][1]) {
                    places[0] = 0;
                    places[1] = 2;
                    return places;
                } else if (board[0][1] == notSame && board[1][0] == board[0][1]) {
                    places[0] = 0;
                    places[1] = 0;
                    return places;
                } else if (board[1][2] == notSame && board[2][1] == board[1][2]) {
                    places[0] = 2;
                    places[1] = 2;
                    return places;
                } else if (board[1][0] == notSame && board[2][1] == board[1][0]) {
                    places[0] = 2;
                    places[1] = 0;
                    return places;
                }
            } else if (board[1][1] == notSame) {
                if ((board[0][0] == notSame && board[2][2] == same) || (board[2][2] == notSame && board[0][0] == same)) {
                    int x = random.nextInt(2);
                    switch (x) {
                        case 1:
                            places[0] = 0;
                            places[1] = 2;
                            break;
                        case 0:
                            places[0] = 2;
                            places[1] = 0;
                            break;
                    }
                    return places;
                } else if ((board[2][0] == notSame && board[0][2] == same) || (board[0][2] == notSame && board[2][0] == same)) {
                    int x = random.nextInt(2);
                    switch (x) {
                        case 1:
                            places[0] = 0;
                            places[1] = 0;
                            break;
                        case 0:
                            places[0] = 2;
                            places[1] = 2;
                            break;
                    }
                    return places;
                }
            }
        } else if (slots.size() == 4) {
            if (board[0][0] == same && board[2][0] == same && board[1][0] == notSame && board[2][1] == notSame) {
                int x = random.nextInt(2);
                switch (x) {
                    case 1:
                        places[0] = 1;
                        places[1] = 1;
                        break;
                    case 0:
                        places[0] = 0;
                        places[1] = 2;
                        break;
                }
                return places;
            } else if (board[0][0] == same && board[0][2] == same && board[0][1] == notSame && board[1][0] == notSame) {
                int x = random.nextInt(2);
                switch (x) {
                    case 1:
                        places[0] = 1;
                        places[1] = 1;
                        break;
                    case 0:
                        places[0] = 2;
                        places[1] = 2;
                        break;
                }
                return places;
            } else if (board[0][2] == same && board[2][2] == same && board[2][1] == notSame && board[1][2] == notSame) {
                int x = random.nextInt(2);
                switch (x) {
                    case 1:
                        places[0] = 1;
                        places[1] = 1;
                        break;
                    case 0:
                        places[0] = 0;
                        places[1] = 0;
                        break;
                }
                return places;
            } else if (board[0][2] == same && board[2][2] == same && board[0][1] == notSame && board[1][2] == notSame) {
                int x = random.nextInt(2);
                switch (x) {
                    case 1:
                        places[0] = 1;
                        places[1] = 1;
                        break;
                    case 0:
                        places[0] = 2;
                        places[1] = 0;
                        break;
                }
                return places;
            } else if (board[0][0] == same && board[2][0] == same && board[1][0] == notSame && board[0][1] == notSame) {
                int x = random.nextInt(2);
                switch (x) {
                    case 1:
                        places[0] = 1;
                        places[1] = 1;
                        break;
                    case 0:
                        places[0] = 2;
                        places[1] = 2;
                        break;
                }
                return places;
            } else if (board[0][0] == same && board[0][2] == same && board[0][1] == notSame && board[1][2] == notSame) {
                int x = random.nextInt(2);
                switch (x) {
                    case 1:
                        places[0] = 1;
                        places[1] = 1;
                        break;
                    case 0:
                        places[0] = 2;
                        places[1] = 0;
                        break;
                }
                return places;
            } else if (board[2][0] == same && board[2][2] == same && board[2][1] == notSame && board[1][2] == notSame) {
                int x = random.nextInt(2);
                switch (x) {
                    case 1:
                        places[0] = 1;
                        places[1] = 1;
                        break;
                    case 0:
                        places[0] = 0;
                        places[1] = 0;
                        break;
                }
                return places;
            } else if (board[2][0] == same && board[2][2] == same && board[1][0] == notSame && board[2][1] == notSame) {
                int x = random.nextInt(2);
                switch (x) {
                    case 1:
                        places[0] = 1;
                        places[1] = 1;
                        break;
                    case 0:
                        places[0] = 0;
                        places[1] = 2;
                        break;
                }
                return places;
            } else if (board[2][2] == same && board[0][2] == same && board[1][2] == notSame && board[2][0] == notSame) {
                places[0] = 0;
                places[1] = 0;
                return places;
            } else if (board[2][2] == same && board[2][0] == same && board[2][1] == notSame && board[0][0] == notSame) {
                places[0] = 0;
                places[1] = 2;
                return places;
            } else if (board[0][0] == same && board[2][0] == same && board[1][0] == notSame && board[0][2] == notSame) {
                places[0] = 2;
                places[1] = 2;
                return places;
            } else if (board[0][0] == same && board[0][2] == same && board[0][1] == notSame && board[2][2] == notSame) {
                places[0] = 2;
                places[1] = 0;
                return places;
            } else if (board[0][0] == same && board[0][2] == same && board[0][1] == notSame && board[2][0] == notSame) {
                places[0] = 2;
                places[1] = 2;
                return places;
            } else if (board[2][2] == same && board[0][2] == same && board[1][2] == notSame && board[0][0] == notSame) {
                places[0] = 2;
                places[1] = 0;
                return places;
            } else if (board[2][2] == same && board[2][0] == same && board[2][1] == notSame && board[0][2] == notSame) {
                places[0] = 0;
                places[1] = 0;
                return places;
            } else if (board[0][0] == same && board[2][0] == same && board[1][0] == notSame && board[2][2] == notSame) {
                places[0] = 0;
                places[1] = 2;
                return places;
            }
        }
        System.out.println("ENTRARÁ NO DIFÍCIL");
        return hard(board);
    }
}