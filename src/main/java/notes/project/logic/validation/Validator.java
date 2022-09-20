package notes.project.logic.validation;

public interface Validator<T> {
    void validate(T source);
}
