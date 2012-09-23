package org.vaadin.demo.crm.ui;

import org.vaadin.demo.crm.data.Record;

import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Switch;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class DetailsView extends NavigationView {

	public void setRecord(EntityItem<? extends Record> record) {
		getNavigationBar().setCaption(record.getEntity().getRecordType());

		VerticalLayout lo = new VerticalLayout();
		setContent(lo);

		VerticalComponentGroup formGroup = new VerticalComponentGroup();
		formGroup.setCaption(record.getEntity().getRecordName());
		lo.addComponent(formGroup);
		final Form form = new Form();
		formGroup.addComponent(form);
		form.setFormFieldFactory(new DefailsFieldFactory());
		form.setWriteThrough(false);
		form.setItemDataSource(record);

		getNavigationBar().setRightComponent(
				new Button("Save", new Button.ClickListener() {
					public void buttonClick(ClickEvent event) {
						form.commit();
					}
				}));
		getNavigationBar().setLeftComponent(
				new Button("Discard", new Button.ClickListener() {
					public void buttonClick(ClickEvent event) {
						form.discard();
					}
				}));
	}

	/* Custom mobile field types */
	class DefailsFieldFactory extends FieldFactory {

		protected Field createManyToOneField(
				@SuppressWarnings("rawtypes") EntityContainer containerForProperty, Object itemId,
				Object propertyId, Component uiContext) {
			Field f = super.createManyToOneField(containerForProperty, itemId, propertyId,
					uiContext);
			if (Record.class.isAssignableFrom(containerForProperty.getType(propertyId)) && f instanceof AbstractSelect)
				((AbstractSelect)f).setItemCaptionPropertyId("recordName");
			return f;
		}

		public Field createField(Item item, Object propertyId,
				Component uiContext) {
			if (propertyId.equals("recordName")
					|| propertyId.equals("recordTypePlural")
					|| propertyId.equals("id")
					|| propertyId.equals("recordType"))
				return null;
			Property p = item.getItemProperty(propertyId);
			if (p.getType() == Boolean.class)
				return new Switch("" + propertyId, p);
			if (p.getType() == String.class && propertyId.equals("description")) {
				TextArea a = new TextArea("" + propertyId, p);
				a.setNullRepresentation("");
				a.setWidth("100%");
				a.setRows(10);
				return a;
			}
			return super.createField(item, propertyId, uiContext);
		}
	}
}
