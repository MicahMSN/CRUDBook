package org.mosin.crud;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@SpringUI
public class BookUI extends UI {

    final TextField filter;

    private final Button addNewBtn;
    private final BookRepository repo;
    private final BookEditor editor;
    private final Grid<Book> grid;

    @Autowired
    public BookUI(BookRepository repo, BookEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(Book.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New book", VaadinIcons.PLUS);
    }

    @Override
    protected void init(VaadinRequest request) {
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);
        setContent(mainLayout);
        grid.setHeight(80, Unit.PERCENTAGE);
        grid.setWidth(100, Unit.PERCENTAGE);
        grid.setColumns();
        grid.setColumns("id", "title", "author","printYear", "description", "isbn","readAlready");
        filter.setPlaceholder("Filter by title");

        // READ
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(v -> listBooks(v.getValue()));

        // UPDATE DELETE
        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editBook(e.getValue());
        });

        // CREATE
        addNewBtn.addClickListener(e -> editor.editBook(new Book("", "", "", "", "", false)));

        // read by book title
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listBooks(filter.getValue());
        });

        // Initialize listing
        listBooks(null);

    }

    void listBooks(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        }
        else {
            grid.setItems(repo.findByTitleStartsWithIgnoreCase(filterText));
        }
    }



}
