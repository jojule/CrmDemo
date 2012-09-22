package org.vaadin.demo.crm.ui;

import org.vaadin.demo.crm.data.Record;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Switch;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public class DetailsView extends NavigationView implements FormFieldFactory {

	public void setRecord(Record record) {
		getNavigationBar().setCaption(record.getRecordType());
		Form form = new Form();
		form.getLayout().setMargin(true);
		setContent(form);
		form.setFormFieldFactory(this);
		form.setItemDataSource(new BeanItem<Object>(record));
	}

	/* Custom mobile field types */
	public Field createField(Item item, Object propertyId, Component uiContext) {
		Property p = item.getItemProperty(propertyId);
		if (propertyId.equals("recordName")
				|| propertyId.equals("recordTypePlural")
				|| propertyId.equals("recordType"))
			return null;
		if (p.getType() == Boolean.class)
			return new Switch("" + propertyId, p);
		Field defaultField = DefaultFieldFactory.get().createField(item,
				propertyId, uiContext);
		if (defaultField instanceof TextField)
			((TextField) defaultField).setNullRepresentation("");
		return defaultField;
	}
}
