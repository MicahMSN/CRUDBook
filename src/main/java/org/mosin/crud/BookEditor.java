package org.mosin.crud;


import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class BookEditor extends VerticalLayout {

    private final BookRepository repository;
    private Book book;


    private TextField title = new TextField("Title");
    private TextField author = new TextField("Author");
    private TextField description = new TextField("Description");
    private TextField isbn = new TextField("ISBN");
    private TextField printYear = new TextField("Year");


    private Button save = new Button("Save", VaadinIcons.CHECK);
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete", VaadinIcons.TRASH);
    private Button read = new Button("Read", VaadinIcons.EYE);
    private CssLayout actions = new CssLayout(save, cancel, delete, read);
    private Binder<Book> binder = new Binder<>(Book.class);

    @Autowired
    public BookEditor(BookRepository repository) {
        this.repository = repository;
        addComponents(title, author, description, isbn, printYear, actions);
        binder.bindInstanceFields(this);
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        read.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        read.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.addClickListener((Button.ClickListener) event -> {
                book.setReadAlready(false);
                repository.save(book);
        });
        delete.addClickListener(e -> repository.delete(book));
        cancel.addClickListener(e -> editBook(book));

        read.addClickListener((Button.ClickListener) event -> {
            if(!book.getReadAlready()){
                book.setReadAlready(true);
                repository.save(book);
            }
        });
        setVisible(false);

    }

    public void editBook(Book eBook) {
        if (eBook == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = (eBook.getId() != null);

        if (persisted) {
            // Find fresh entity for editing
            book = repository.findById(eBook.getId()).get();
            author.setVisible(false);
        } else {
            book = eBook;
            author.setVisible(true);
        }
        cancel.setVisible(persisted);
        if(eBook.getReadAlready()){
            read.setVisible(false);
        } else {
            read.setVisible(true);
        }
        binder.setBean(book);
        setVisible(true);
        save.focus();
        read.focus();
        title.selectAll();
    }

    public void setChangeHandler(ChangeHandler h) {
        save.addClickListener(e -> h.onChange());
        read.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }
    @FunctionalInterface
    interface ChangeHandler{
        void onChange();
    }
}
