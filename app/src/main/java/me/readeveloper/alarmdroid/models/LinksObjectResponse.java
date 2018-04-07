package me.readeveloper.alarmdroid.models;

public class LinksObjectResponse {
    private String first;
    private String last;
    private int prev;
    private int next;

    public String getFirst() {
        return this.first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return this.last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getPrev() {
        return this.prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public int getNext() {
        return this.next;
    }

    public void setNext(int next) {
        this.next = next;
    }
}
