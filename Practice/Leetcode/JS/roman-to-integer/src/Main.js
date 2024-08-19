class Main {

    transformRomanToInteger(romans) {
        const numbers = [];
        const romansArray = [...this.getSymbolsFromRomans(romans)]

        if (this.hasOnlyOneSymbol(romans)) {
            return this.getNumberFromRoman(romans)
        }

        for (let i = 0; i < romansArray.length; i++) {
            const currentRoman = romansArray[i];
            const nextRoman = romansArray[i + 1];

            if (i + 1 < romansArray.length && this.isSubstractiveCombination(currentRoman, nextRoman)) {
                numbers.push(this.getRomanSubstrat(currentRoman, nextRoman));
                romansArray.splice(i + 1, 1)
            } else {
                numbers.push(this.getNumberFromRoman(currentRoman));
            }
        }

        return this.sumNumbersValues(numbers);
    }

    getSymbolsFromRomans(romans) {
        return romans.split('');
    }

    getNumberFromRoman(roman) {
        return this.getRomanValues().get(roman);
    }

    hasOnlyOneSymbol(roman) {
        return roman.split('').length === 1;
    }

    sumNumbersValues(numbers) {
        return numbers.reduce((accumulator, currentValue) => accumulator + currentValue, 0);
    }

    isSubstractiveCombination(currentValue, nextValue) {
        return this.getRomanValues().has(this.joinRomanLetters(currentValue, nextValue))
    }

    getRomanSubstrat(currentValue, nextValue) {
        return this.getRomanValues().get(this.joinRomanLetters(currentValue, nextValue))
    }

    joinRomanLetters(currentValue, nextValue) {
        return currentValue.concat(nextValue);
    }

    getRomanValues() {
        return new Map([
            ["I", 1],
            ["V", 5],
            ["X", 10],
            ["L", 50],
            ["C", 100],
            ["D", 500],
            ["M", 1000],
            ["IV", 4],
            ["IX", 9],
            ["XL", 40],
            ["XC", 90],
            ["CD", 400],
            ["CM", 900]
        ]);
    }
}

module.exports = Main;