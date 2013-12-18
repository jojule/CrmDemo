package org.vaadin.demo.crm.ui;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;
import org.vaadin.demo.crm.data.Account;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.touchkit.gwt.client.vcom.navigation.NavigationViewServerRpc;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.NumberField;
import com.vaadin.addon.touchkit.ui.Switch;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.server.ClientMethodInvocation;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Extension;
import com.vaadin.server.Resource;
import com.vaadin.server.ServerRpcManager;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Form;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;

public class AccountView extends NavigationView {
	
	private EntityItem<Account> account;

	public AccountView(EntityItem<Account> account) {
		this.account = account;
		final FieldGroup g= new FieldGroup(account);
		g.setBuffered(true);
		AccountData form = new AccountData();
		g.bindMemberFields(form );
		setContent(form);
		
		getNavigationBar().setRightComponent(new Button("save", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					g.commit();
					getNavigationManager().navigateBack();
					Notification.show("saved", Type.TRAY_NOTIFICATION);
				} catch (CommitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}));
	}

	class AccountData extends VerticalComponentGroup {
		TextField name = new TextField("Customer");
		TextField sales = new TextField("Total sales");
		Switch active = new Switch("Active");
		public AccountData() {
			addComponent(name);
			addComponent(sales);
			addComponent(active);
		}
		
	}
}
