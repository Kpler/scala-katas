def my_sum(a, b):
    return a + b


def distribution(my_lst: list[int]) -> dict[int, int]:
    to_return = {}
    for i in my_lst:
        to_return[i] = to_return.get(i, 0) + 1
    return to_return


if __name__ == "__main__":
    print(my_sum(4, 5))

    a = 2
    print(a)

    a = 3
    print(a)

    lst = [i for i in range(10)]
    print(lst)

    lst[0] = 6
    print(distribution(lst))

