def my_sum(a: int, b: int) -> int:
    return a + b


def distribution(my_lst: list[int]) -> dict[int, int]:
    to_return = {}
    for i in my_lst:
        to_return[i] = to_return.get(i, 0) + 1
    return to_return


def insert_sort(my_lst: list[int]) -> None:
    for i in range(1, len(my_lst)):
        j = i
        while j > 0 and my_lst[j - 1] > my_lst[j]:
            temp = my_lst[j]
            my_lst[j] = my_lst[j - 1]
            my_lst[j - 1] = temp
            j = j - 1


if __name__ == '__main__':
    print(my_sum(2, 3))

    a = 3
    print(a)
    a = 2

    lst = [1, 2, 3, 4]
    print(lst)
    print(lst[1])
    lst[1] = 5
    print(lst)

    toDistrib = [5, 2, 3, 2, 3, 5, 5, 3, 3, 6]
    print(distribution(toDistrib))
    insert_sort(toDistrib)
    print(toDistrib)
