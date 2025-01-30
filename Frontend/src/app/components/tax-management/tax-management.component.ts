import { Component, OnInit } from '@angular/core';
import { TaxService } from '../../services/tax.service';
import { Tax } from '../../models/tax-management.model';

@Component({
  selector: 'app-tax-management',
  templateUrl: './tax-management.component.html',
  styleUrls: ['./tax-management.component.css']
})
export class TaxManagementComponent implements OnInit {
  taxes: Tax[] = [];
  selectedTax: Tax = {
    taxFormId: 0,
    formType: '',
    filingDate: new Date(),
    totalTaxAmount: 0,
    userId: 0
  };
  dateError: boolean=false;
  isUpdateMode: boolean = false; // Flag to toggle between Add and Update modes

  constructor(private taxService: TaxService) { }

  ngOnInit(): void {
    this.loadTaxes();
  }

  // Method to validate the date
  validateDate(event: any): void {
    const selectedDate = new Date(event.target.value);
    const today = new Date();
    this.dateError = selectedDate > today; // Check if the date is in the future
  }
  loadTaxes(): void {
    this.taxService.getAllTaxes().subscribe(
      (data:Tax[]) => {
        this.taxes = data;
        console.log(data);
      }
    );
  }

  addTax(): void {
    this.taxService.createTax(this.selectedTax).subscribe(
      (res)=>{
        this.loadTaxes();
        this.selectedTax = this.createEmptyTax(); // Clear the form
      },
      (error)=>{
        console.error('Error creating tax:', error);
      }
    )
    
  }
  updateTaxForm(id:number): void {
    this.taxService.getTaxById(id).subscribe(
      (t: Tax) => {
        console.log(t);
        this.selectedTax = {...t};
        this.  isUpdateMode=true;
      },
      (error)=>{
        console.error('Error retrieving tax:', error);
      }
    )
    
  }
  updateTax():void{
    this.taxService.updateTax(this.selectedTax.taxFormId,this.selectedTax).subscribe(
      (res) => {
        this.loadTaxes();
        this.selectedTax = this.createEmptyTax(); // Clear the form
        this.  isUpdateMode=false
      },
      (error)=>{
        console.error('Error updating tax:', error);
      }
    )
  }
  

  deleteTax(id: number): void {
    this.taxService.deleteTax(id).subscribe(
      (response)=>{
        this.loadTaxes();
        
      },
      (error)=>{
        console.error('Error deleting tax:', error);
      }
    )
  }

  selectTax(tax: Tax): void {
    this.selectedTax={...tax};
  }

  createEmptyTax(): Tax {
    return {
      taxFormId: 0,
      formType: '',
      filingDate: new Date(),
      totalTaxAmount: 0,
      userId: 0
    };
  }
}
