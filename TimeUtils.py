from datetime import datetime


class TimeUtils:
    @staticmethod
    def current_datetime():
        return datetime.now().strftime('%Y-%m-%d %H:%M:%S')

