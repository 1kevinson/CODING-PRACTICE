class Main {

    longestCommonPrefix(words) {
        let longestCommonPrefix = '';
        let prefix = '';

        for (let i = 0; i < this.getMinLengthString(words).length; i++) {
            prefix += words[0][i];

            if (words.every(item => item.startsWith(prefix))) {
                longestCommonPrefix = prefix;
            } else {
                break;
            }
        }

        return longestCommonPrefix;
    }

    getMinLengthString(strings) {
        if (strings.length === 0) return '';

        return strings.reduce((minStr, currentStr) =>
            currentStr.length < minStr.length ? currentStr : minStr
        );
    }

}

module.exports = Main;