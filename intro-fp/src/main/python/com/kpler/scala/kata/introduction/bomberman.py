#!/bin/python3

#
# Complete the 'bomberMan' function below.
#
# The function is expected to return a STRING_ARRAY.
# The function accepts following parameters:
#  1. INTEGER n
#  2. STRING_ARRAY grid
#

Board = list[str]
Point = tuple[int, int]


def neighbors(p: Point, width: int, height: int) -> list[Point]:
    (x, y) = p
    return [(i, y) for i in [x - 1, x, x + 1] if 0 <= i < width] + \
        [(x, j) for j in [y - 1, y + 1] if 0 <= j < height]


def detonate(bombs: list[Point], board: Board) -> Board:
    height = len(board)
    width = len(board[0])
    board_copy = board.copy()
    all_exploded_cells = {(i, j) for bomb in bombs for (i, j) in neighbors(bomb, width, height)}
    for (i, j) in all_exploded_cells:
        row: str = board_copy[j]
        board_copy[j] = row[:i] + '.' + row[(i + 1):]
    return board_copy


def get_bombs(board: Board) -> list[Point]:
    height = len(board)
    width = len(board[0])
    return [(i, j) for i in range(width) for j in range(height) if board[j][i] == 'O']


def bomber_man(nb_seconds: int, board: Board) -> Board:
    height = len(board)
    width = len(board[0])
    bombs = get_bombs(board)
    full_of_bombs: Board = ['O' * width] * height

    board_after_first_explode = detonate(bombs, full_of_bombs)

    """
    0: board
    1: board
    2: full_of_bombs
    3: board_after_first_explode (3 % 4 == 3)
    4: full_of_bombs
    5: other_explode_state (5 % 4 == 1)
    6: full_of_bombs
    7: board_after_first_explode (7 % 4 == 3)
    8: full_of_bombs
    9: other_explode_state (9 % 4 == 1)
    """

    if nb_seconds == 0 or nb_seconds == 1:
        return board
    else:
        if nb_seconds % 2 == 0:
            return full_of_bombs
        elif nb_seconds % 4 == 3:
            return board_after_first_explode
        else:
            return board


if __name__ == '__main__':
    with open('bomberman-input.txt', mode='r') as input_file:
        all_lines = input_file.readlines()
        first_line = all_lines[0].rstrip().split()

        r = int(first_line[0])
        c = int(first_line[1])
        n = int(first_line[2])

        grid = [all_lines[i] for i in range(1, r)]

        result = bomber_man(n, grid)

        with open('bomberman-output.txt', mode='w') as output_file:
            output_file.write('\n'.join(result))
            output_file.write('\n')
