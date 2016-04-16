import {Component} from 'angular2/core';
import {OnInit} from 'angular2/core';
import {XsdService} from './xsd.service';

@Component({
    selector 	: 'my-app',
    providers 	: [ XsdService ],
    bindings 	: [XsdService],
    templateUrl : 'templates/app.html' 
})

export class AppComponent implements OnInit { 
	
	constructor(public xsdService: XsdService) { }

	ngOnInit() {
		this.xsdService.list().subscribe(
			data => {
				console.log(data);
			}
		);
	}
	 
}
