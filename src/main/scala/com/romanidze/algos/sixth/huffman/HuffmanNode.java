package com.romanidze.algos.sixth.huffman;

import java.util.Objects;

/**
 * 07.11.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class HuffmanNode {

    private int frequency;
    private char character;

    private HuffmanNode left;
    private HuffmanNode right;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HuffmanNode)) return false;
        HuffmanNode that = (HuffmanNode) o;
        return getFrequency() == that.getFrequency() &&
                getCharacter() == that.getCharacter() &&
                Objects.equals(getLeft(), that.getLeft()) &&
                Objects.equals(getRight(), that.getRight());
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "frequency=" + frequency +
                ", character=" + character +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrequency(), getCharacter(), getLeft(), getRight());
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }
}
