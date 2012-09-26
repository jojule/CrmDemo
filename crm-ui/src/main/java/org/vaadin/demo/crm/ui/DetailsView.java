package org.vaadin.demo.crm.ui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.vaadin.demo.crm.data.Record;

import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Switch;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.TextArea;

public class DetailsView extends NavigationView {

	Form recordForm = new Form();
	VerticalComponentGroup formGroup = new VerticalComponentGroup();
	Button saveButton = new Button("Save", new ClickListener() {
		public void buttonClick(ClickEvent event) {
			recordForm.commit();
			if (updateListener != null)
				updateListener.recordUpdatedByDetailsView();
		}
	});
	Button discardButton = new Button("Discard", new ClickListener() {
		public void buttonClick(ClickEvent event) {
			recordForm.discard();
		}
	});
	UpdateListener updateListener;

	public DetailsView() {
		getNavigationBar().setRightComponent(saveButton);
		getNavigationBar().setLeftComponent(discardButton);

		setContent(formGroup);
		formGroup.addComponent(recordForm);

		recordForm.setFormFieldFactory(new DefailsFieldFactory());
		recordForm.setWriteThrough(false);
	}

	public void setRecord(EntityItem<? extends Record> record,
			UpdateListener listener) {
		getNavigationBar().setCaption(record.getEntity().getRecordType());
		formGroup.setCaption(record.getEntity().getRecordName());

		recordForm.setItemDataSource(record);
		sortFormFields();
		updateListener = listener;

	}

	// Customize the fields in the form
	static class DefailsFieldFactory extends FieldFactory {

		// Show record names properly in entity link dropdowns
		protected Field createManyToOneField(
				@SuppressWarnings("rawtypes") EntityContainer containerForProperty,
				Object itemId, Object propertyId, Component uiContext) {
			Field f = super.createManyToOneField(containerForProperty, itemId,
					propertyId, uiContext);
			if (Record.class.isAssignableFrom(containerForProperty
					.getType(propertyId)) && f instanceof AbstractSelect)
				((AbstractSelect) f).setItemCaptionPropertyId("recordName");
			return f;
		}

		static final Set<Object> hiddenProperties = new HashSet<Object>(
				Arrays.asList("recordName", "recordTypePlural", "id",
						"recordType"));

		public Field createField(Item item, Object propertyId, Component context) {

			// Hide technical fields from form
			if (hiddenProperties.contains(propertyId))
				return null;

			Property fieldDatasource = item.getItemProperty(propertyId);
			// Use IOS style switch instead of checkbox created by default
			if (fieldDatasource.getType() == Boolean.class) {
				return new Switch(createCaptionByPropertyId(propertyId),
						fieldDatasource);
			}

			if (fieldDatasource.getType() == String.class
					&& propertyId.equals("description")) {
				TextArea a = new TextArea("Description", fieldDatasource);
				a.setNullRepresentation("");
				a.setWidth("100%");
				a.setRows(10);
				return a;
			}

			// Resize the fields to be 100% wide
			Field field = super.createField(item, propertyId, context);
			field.setWidth("100%");
			return field;
		}
	}

	/** Array containing properties that should displayed on top of forms */
	private static final String[] prioritizeProperties = { "name", "lastName",
			"firstName" };

	private void sortFormFields() {
		// Ensure important fields are displayed first, let others be in
		// whatever order jvm desides to list them. For more explicit ordering
		// developers should use Form.setItemDataSource(Item, Collection);
		AbstractOrderedLayout layout = (AbstractOrderedLayout) recordForm
				.getLayout();
		for (String p : prioritizeProperties) {
			Field namefield = recordForm.getField(p);
			if (namefield != null)
				layout.addComponentAsFirst(namefield);
		}
	}

	interface UpdateListener {
		public void recordUpdatedByDetailsView();
	}
}
