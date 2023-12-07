class TimeUtils {
    static currentDatetime() {
        return new Date().toISOString();
    }
}

module.exports = { TimeUtils };
