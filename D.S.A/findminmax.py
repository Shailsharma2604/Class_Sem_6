class Pair:
    def __init__(self, min_val, max_val):
        self.min = min_val
        self.max = max_val

# To count the number of comparisons
comparisons = 0

def find_min_max(arr, low, high):
    global comparisons

    # If there's only one element
    if low == high:
        return Pair(arr[low], arr[low])

    # If there are two elements
    if high == low + 1:
        comparisons += 1
        if arr[low] > arr[high]:
            return Pair(arr[high], arr[low])
        else:
            return Pair(arr[low], arr[high])

    # Divide and conquer
    mid = (low + high) // 2
    left = find_min_max(arr, low, mid)
    right = find_min_max(arr, mid + 1, high)

    # Combine results
    comparisons += 2
    min_val = min(left.min, right.min)
    max_val = max(left.max, right.max)

    return Pair(min_val, max_val)

# Main function
def main():
    global comparisons

    n = int(input("Enter the number of elements in the array: "))

    if n <= 0:
        print("Array size must be greater than 0.")
        return

    arr = list(map(int, input("Enter the elements separated by space: ").split()))

    if len(arr) != n:
        print(f"Error: You entered {len(arr)} elements, but expected {n}.")
        return

    # Reset comparisons before starting
    comparisons = 0

    result = find_min_max(arr, 0, n - 1)

    print("\nMinimum element:", result.min)
    print("Maximum element:", result.max)
    print("Number of comparisons:", comparisons)

# Run the program
if __name__ == "__main__":
    main()
