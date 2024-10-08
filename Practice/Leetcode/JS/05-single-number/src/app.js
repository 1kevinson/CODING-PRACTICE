export class LeetCode {

    singleNumber(numbers) {
        const numbersFilter = [];

        for (const number of numbers) {
            if (numbersFilter.includes(number)) {
                numbersFilter.splice(numbersFilter.indexOf(number), 1);
                continue;
            }

            numbersFilter.push(number);
        }

        return numbersFilter[0];
    }
}
