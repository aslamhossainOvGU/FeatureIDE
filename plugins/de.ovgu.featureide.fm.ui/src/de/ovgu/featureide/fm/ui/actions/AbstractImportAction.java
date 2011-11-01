/* FeatureIDE - An IDE to support feature-oriented software development
 * Copyright (C) 2005-2011  FeatureIDE Team, University of Magdeburg
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see http://www.gnu.org/licenses/.
 *
 * See http://www.fosd.de/featureide/ for further information.
 */
package de.ovgu.featureide.fm.ui.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.io.IFeatureModelReader;
import de.ovgu.featureide.fm.core.io.UnsupportedModelException;
import de.ovgu.featureide.fm.core.io.xml.XmlFeatureModelWriter;
import de.ovgu.featureide.fm.ui.FMUIPlugin;

/**
 * abstract class with core functionality to import FeatureModels Implementing
 * classes mainly provide a specific FeatureModelReader
 * 
 * @author Fabian Benduhn
 */
public abstract class AbstractImportAction implements IObjectActionDelegate {
	private ISelection selection;

	private IFeatureModelReader modelReader;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {

	}

	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			for (Iterator<?> it = ((IStructuredSelection) selection).iterator(); it
					.hasNext();) {
				Object element = it.next();
				IFile outputFile = null;
				if (element instanceof IFile) {
					outputFile = (IFile) element;
				} else if (element instanceof IAdaptable) {
					outputFile = (IFile) ((IAdaptable) element)
							.getAdapter(IFile.class);
				}
				if (outputFile != null) {
					try {

						boolean proceed = MessageDialog
								.openQuestion(new Shell(), "Warning!",
										"This will override the current model irrepealable! Proceed?");
						if (proceed) {

							FileDialog fileDialog = new FileDialog(new Shell(),
									SWT.OPEN);
							fileDialog.setOverwrite(false);

							String filepath = fileDialog.open();
							if (filepath == null)
								return;
							File inputFile = new File(filepath);

							while (!inputFile.exists()) {
								MessageDialog.openInformation(new Shell(),
										"File " + "not Found",
										"Specified file wasn't found");
								inputFile = new File(fileDialog.open());
							}

							FeatureModel fm = new FeatureModel();
							modelReader = setModelReader(fm);
							modelReader.readFromFile(inputFile);
							XmlFeatureModelWriter fmWriter = new XmlFeatureModelWriter(
									fm);
							fmWriter.writeToFile(outputFile);
							outputFile.refreshLocal(IResource.DEPTH_ZERO, null);
							IWorkbenchWindow window = PlatformUI.getWorkbench()
									.getActiveWorkbenchWindow();
							IWorkbenchPage page = window.getActivePage();
							IDE.openEditor(page, outputFile);

						}
					} catch (FileNotFoundException e) {
						FMUIPlugin.getDefault().logError(e);
					} catch (UnsupportedModelException e) {
						String errStr = e.getMessage();
						MessageDialog.openWarning(new Shell(), "Warning!",
								"Error while loading file: \n " + errStr);
						FMUIPlugin.getDefault().logError(e);
					} catch (CoreException e) {
						FMUIPlugin.getDefault().logError(e);
					}
				}
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	/**
	 * returns an instance of IFeatureModelReader
	 * 
	 * @param fm
	 *            the feature model to initialize the IFeatureModelReader
	 */
	abstract IFeatureModelReader setModelReader(FeatureModel fm);
}