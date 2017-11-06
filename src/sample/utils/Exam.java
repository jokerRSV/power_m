package sample.utils;


public enum Exam {
    Login {
        @Override
        String getTitle() {
            return "loginnn";
        }
    }, Main {
        @Override
        String getTitle() {
            return "mainnn";
        }
    };

    abstract String getTitle();
}
